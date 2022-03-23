package com.crm.service.impl;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.exception.AccountIsActivatedException;
import com.crm.exception.ElementAlreadyExistException;
import com.crm.exception.ErrorDict;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
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

import static com.crm.exception.ErrorDict.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final EmailUtils emailUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final VerificationTokenService verificationTokenService;

    private static final String USER_ROLE = "ROLE_USER";

    @Transactional
    @Override
    public void save(final NewAccountRequest newAccountRequest) {
        AccountEntity accountEntity = setUpAccountEntity(newAccountRequest);

        checkIsAccountAlreadyExistsByCredentials(accountEntity);

        AccountEntity account = accountRepository.save(accountEntity);
        String token = verificationTokenService.saveVerificationToken(account);
        emailUtils.sendEmail(account, token);
    }

    @Override
    public AccountEntity findById(final Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ACCOUNT_DOES_NOT_EXIST));
    }

    @Override
    public AccountEntity findByLogin(final String login) {
        return accountRepository.findByLogin(login).orElseThrow(() -> new NoSuchElementException(ACCOUNT_DOES_NOT_EXIST));
    }

    @Override
    public boolean accountExistsByLogin(final String login) {
        return accountRepository.existsByLogin(login);
    }

    @Override
    public boolean accountExistsByEmail(final String email) {
        return accountRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public AccountResponse activateAccount(final String token) {
        VerificationTokenEntity vte = verificationTokenService.getVerificationToken(token);

        AccountEntity accountEntity = findById(vte.getUser().getId());
        if (Boolean.TRUE.equals(accountEntity.getIsActive())) {
            throw new AccountIsActivatedException(ACCOUNT_IS_ACTIVE);
        }
        accountEntity.setIsActive(true);
        accountRepository.save(accountEntity);
        return accountMapper.convertToDto(accountEntity);
    }

    private AccountEntity setUpAccountEntity(final NewAccountRequest newAccountRequest) {
        return AccountEntity.builder()
                .email(newAccountRequest.getEmail())
                .login(newAccountRequest.getLogin())
                .password(passwordEncoder.encode(newAccountRequest.getPassword()))
                .registrationDate(newAccountRequest.getRegistrationDate())
                .role(getDefaultUserRole())
                .loginAttempts(0)
                .build();
    }

    private void checkIsAccountAlreadyExistsByCredentials(final AccountEntity accountEntity) {
        if (accountExistsByEmail(accountEntity.getEmail())) {
            throw new ElementAlreadyExistException(ACCOUNT_ALREADY_EXIST_BY_EMAIL);
        }

        if (accountExistsByLogin(accountEntity.getLogin())) {
            throw new ElementAlreadyExistException(ACCOUNT_ALREADY_EXIST_BY_LOGIN);
        }
    }

    private RoleEntity getDefaultUserRole() {
        return roleRepository.findByName(USER_ROLE).orElseThrow(() -> new NoSuchElementException(ErrorDict.ROLE_DOES_NOT_EXIST));
    }
}
