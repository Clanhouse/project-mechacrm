package com.crm.service;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.AccountRequest;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountDetailsAdapter;
import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements UserDetailsService, AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse save(final NewAccountRequest newAccountRequest) {
        AccountEntity accountEntity = accountMapper.convertToEntity(newAccountRequest);
        AccountEntity save = accountRepository.save(accountEntity);
        return accountMapper.convertToDto(save);
    }

    @Override
    public AccountEntity findById(final Long id) {
        Optional<AccountEntity> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new NoSuchElementException("Konto o takim ID nie istnieje"));
    }

    @Override
    public AccountEntity findByLogin(final String login) {
        Optional<AccountEntity> account = accountRepository.findByLogin(login);
        return account.orElseThrow(() -> new NoSuchElementException("Konto o takim loginie nie istnieje"));
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        return new AccountDetailsAdapter(findByLogin(s));
    }
}
