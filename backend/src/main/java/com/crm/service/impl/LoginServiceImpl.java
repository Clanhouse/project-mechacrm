package com.crm.service.impl;

import com.crm.exception.UserDisabledException;
import com.crm.exception.user.AccountAlreadyExistException;
import com.crm.exception.user.InvalidCredentialsException;
import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import com.crm.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.crm.exception.ErrorDict.ACCOUNT_ALREADY_EXIST;
import static com.crm.exception.ErrorDict.INVALID_CREDENTIALS;
import static com.crm.exception.ErrorDict.USER_DISABLED;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;

    @Override
    public void increaseAttemptsCounter(final String login) {
        AccountEntity accountEntity = accountRepository.findByLogin(login).orElseThrow(
                () -> new AccountAlreadyExistException(ACCOUNT_ALREADY_EXIST));
        accountEntity.setLoginAttempts(accountEntity.getLoginAttempts() + 1);
        accountEntity.setLastFailedLogin(Timestamp.valueOf(LocalDateTime.now()));
        accountRepository.save(accountEntity);
    }

    @Override
    public void resetAttemptsCounter(final String login) {
        AccountEntity accountEntity = accountRepository.findByLogin(login).orElseThrow(
                () -> new AccountAlreadyExistException(ACCOUNT_ALREADY_EXIST));
        accountEntity.setLoginAttempts(0);
        accountEntity.setLastSuccessfulLogin(Timestamp.valueOf(LocalDateTime.now()));
        accountRepository.save(accountEntity);
    }

    public void authenticate(final String username, final String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            this.resetAttemptsCounter(username);
        } catch (final DisabledException e) {
            throw new UserDisabledException(USER_DISABLED);
        } catch (final BadCredentialsException e) {
            this.increaseAttemptsCounter(username);
            throw new InvalidCredentialsException(INVALID_CREDENTIALS);
        }
    }
}
