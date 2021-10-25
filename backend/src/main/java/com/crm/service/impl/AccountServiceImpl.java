package com.crm.service.impl;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.exception.AccountIsActivatedException;
import com.crm.exception.ElementAlreadyExistException;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.VerificationTokenEntity;
import com.crm.repository.AccountRepository;
import com.crm.repository.RoleRepository;
import com.crm.service.AccountService;
import com.crm.service.VerificationTokenService;
import com.crm.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.crm.exception.ErrorDict.ACCOUNT_ALREADY_EXIST_BY_EMAIL;
import static com.crm.exception.ErrorDict.ACCOUNT_ALREADY_EXIST_BY_LOGIN;
import static com.crm.exception.ErrorDict.ACCOUNT_DOES_NOT_EXIST;
import static com.crm.exception.ErrorDict.ACCOUNT_IS_ACTIVE;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final EmailUtils emailUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final VerificationTokenService verificationTokenService;

    @Transactional
    @Override
    public AccountResponse save(NewAccountRequest newAccountRequest) {
        AccountEntity accountEntity = setUpAccountEntity(newAccountRequest);

        if (isAccountEmail(accountEntity.getEmail())) {
            throw new ElementAlreadyExistException(ACCOUNT_ALREADY_EXIST_BY_EMAIL);
        }

        if (isAccountExist(accountEntity.getLogin())) {
            throw new ElementAlreadyExistException(ACCOUNT_ALREADY_EXIST_BY_LOGIN);
        }

        AccountEntity account = accountRepository.save(accountEntity);
        String token = verificationTokenService.saveVerificationToken(account);
        emailUtils.sendEmail(account, token);
        return accountMapper.convertToDto(account);
    }


    @Override
    public AccountEntity findById(final Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ACCOUNT_DOES_NOT_EXIST));
    }

    @Override
    public boolean isAccountExist(final String login) {
        return accountRepository.existsByLogin(login);
    }

    @Override
    public boolean isAccountEmail(String email) {
        return accountRepository.existsByEmail(email);
    }


    @Transactional
    @Override
    public AccountResponse activateNewAccount(final String token) {
        VerificationTokenEntity vte = verificationTokenService.isTokenNotExpired(token);

        AccountEntity accountEntity = findById(vte.getUser().getId());
        if (accountEntity.getIsActivated() == true) {
            throw new AccountIsActivatedException(ACCOUNT_IS_ACTIVE);
        }
        accountEntity.setIsActivated(true);
        accountRepository.save(accountEntity);
        return accountMapper.convertToDto(accountEntity);
    }

    private AccountEntity setUpAccountEntity(final NewAccountRequest newAccountRequest) {
        return AccountEntity.builder()
                .email(newAccountRequest.getEmail())
                .login(newAccountRequest.getLogin())
                .password(passwordEncoder.encode(newAccountRequest.getPassword()))
                .registrationDate(newAccountRequest.getRegistrationDate())
                .role(roleRepository.findById(2L).orElseThrow(NoSuchElementException::new))
                .loginAttempts(0)
                .isActivated(false)
                .build();
    }
}
