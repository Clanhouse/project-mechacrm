package com.crm.service;

import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public AccountService(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    public AccountEntity saveAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);

    }
}
