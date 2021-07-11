package com.crm.service;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.exception.ErrorDict;
import com.crm.exception.user.AccountAlreadyExistException;
import com.crm.exception.user.AccountIsActiveException;
import com.crm.model.db.AccountDetailsAdapter;
import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import com.crm.tools.emails.EmailGenerator;
import com.crm.tools.emails.EmailSender;
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
    private final EmailGenerator emailGenerator;
    private final EmailSender emailSender;

    @Override
    public AccountResponse save(final NewAccountRequest newAccountRequest) {
        AccountEntity accountEntity = accountMapper.convertToEntity(newAccountRequest);
        try {
            findByLogin(accountEntity.getLogin());
            if (accountEntity.getIsActivated()) {
                throw new AccountIsActiveException(ErrorDict.ACCOUNT_IS_ACTIVE);
            } else {
                throw new AccountAlreadyExistException(ErrorDict.ACCOUNT_ALREADY_EXIST);
            }
        } catch (NoSuchElementException e) {
            AccountEntity account = accountRepository.save(accountEntity);
            emailSender.sendEmail(emailGenerator.generateEmail(account));
            return accountMapper.convertToDto(account);
        }
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

    @Override
    public AccountResponse activateNewAccount(final Long id) {
        AccountEntity accountEntity = findById(id);
        accountEntity.setIsActivated(true);
        accountRepository.save(accountEntity);
        return accountMapper.convertToDto(accountEntity);
    }
}
