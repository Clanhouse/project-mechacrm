package com.crm.service;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.AccountRequest;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
import com.crm.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(PasswordEncoder passwordEncoder, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountEntity saveAccount(AccountRequest accountRequest) {
        AccountEntity accountEntity = accountMapper.convertToEntity(accountRequest);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setRole(new RoleEntity(null,"USER"));
        return accountRepository.save(accountEntity);

    }
}

