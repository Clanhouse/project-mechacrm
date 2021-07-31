package com.crm.service.impl;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.exception.user.AccountAlreadyExistException;
import com.crm.exception.user.AccountIsActiveException;
import com.crm.exception.user.CrmLoginException;
import com.crm.exception.user.NoSuchAccountException;
import com.crm.model.db.AccountDetailsAdapter;
import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import com.crm.repository.RoleRepository;
import com.crm.service.AccountService;
import com.crm.tools.emails.EmailGenerator;
import com.crm.tools.emails.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.crm.exception.ErrorDict.ACCOUNT_ALREADY_EXIST;
import static com.crm.exception.ErrorDict.ACCOUNT_DOES_NOT_EXIST;
import static com.crm.exception.ErrorDict.ACCOUNT_IS_ACTIVE;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements UserDetailsService, AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final EmailGenerator emailGenerator;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public AccountResponse save(final NewAccountRequest newAccountRequest) {
        AccountEntity accountEntity = setUpAccountEntity(newAccountRequest);

        try {
            findByLogin(accountEntity.getLogin());
            if (accountEntity.getIsActivated()) {
                throw new AccountIsActiveException(ACCOUNT_IS_ACTIVE);
            } else {
                throw new AccountAlreadyExistException(ACCOUNT_ALREADY_EXIST);
            }
        } catch (CrmLoginException e) {
            AccountEntity account = accountRepository.save(accountEntity);
            emailSender.sendEmail(emailGenerator.generateEmail(account));
            return accountMapper.convertToDto(account);
        }
    }

    private AccountEntity setUpAccountEntity(final NewAccountRequest newAccountRequest) {
        return AccountEntity.builder()
                .email(newAccountRequest.getEmail())
                .login(newAccountRequest.getLogin())
                .password(passwordEncoder.encode(newAccountRequest.getPassword()))
                .registrationDate(newAccountRequest.getRegistrationDate())
                .role((roleRepository.findById(2L)).orElseThrow(NoSuchElementException::new))
                .loginAttempts(0)
                .isActivated(false)
                .build();
    }

    @Override
    public AccountEntity findById(final Long id) {
        Optional<AccountEntity> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new NoSuchAccountException(ACCOUNT_DOES_NOT_EXIST));
    }

    @Override
    public AccountEntity findByLogin(final String login) {
        Optional<AccountEntity> account = accountRepository.findByLogin(login);
        return account.orElseThrow(() -> new NoSuchAccountException(ACCOUNT_DOES_NOT_EXIST));
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        return new AccountDetailsAdapter(findByLogin(s));
    }

    @Override
    public AccountResponse activateNewAccount(final Long id) {
        AccountEntity accountEntity = findById(id);
        accountEntity.setIsActivated(true);
        accountRepository.save(accountEntity);
        return accountMapper.convertToDto(accountEntity);
    }
}
