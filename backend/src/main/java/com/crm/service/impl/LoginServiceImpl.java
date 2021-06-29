package com.crm.service.impl;

import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import com.crm.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AccountRepository accountRepository;

    @Override
    public void increaseAttemptsCounter(String login) {
        AccountEntity accountEntity = accountRepository.findByLogin(login).orElseThrow(
                () -> new NoSuchElementException("Użytkownik taki nie istnieje"));
        accountEntity.setLoginAttempts(accountEntity.getLoginAttempts() + 1);
        accountEntity.setLastFailedLogin(Timestamp.valueOf(LocalDateTime.now()));
        accountRepository.save(accountEntity);
    }

    @Override
    public void resetAttemptsCounter(String login) {
        AccountEntity accountEntity = accountRepository.findByLogin(login).orElseThrow(
                () -> new NoSuchElementException("Użytkownik taki nie istnieje"));
        accountEntity.setLoginAttempts(0);
        accountEntity.setLastSuccessfulLogin(Timestamp.valueOf(LocalDateTime.now()));
        accountRepository.save(accountEntity);
    }
}
