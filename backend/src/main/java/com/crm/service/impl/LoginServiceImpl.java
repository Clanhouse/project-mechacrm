package com.crm.service.impl;

import com.crm.config.JwtTokenUtils;
import com.crm.dto.response.JwtResponse;
import com.crm.exception.InvalidCredentialsException;
import com.crm.exception.UserDisabledException;
import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import com.crm.service.AccountService;
import com.crm.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.crm.exception.ErrorDict.*;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final AccountService accountService;

    @Override
    public void increaseAttemptsCounter(final String login) {
        AccountEntity accountEntity = accountService.findByLogin(login);
        accountEntity.setLoginAttempts(accountEntity.getLoginAttempts() + 1);
        accountRepository.save(accountEntity);
    }

    @Override
    public void resetAttemptsCounter(final String login) {
        AccountEntity accountEntity = accountService.findByLogin(login);
        accountEntity.setLoginAttempts(0);
        accountEntity.setLastSuccessfulLogin(Timestamp.valueOf(LocalDateTime.now()));
        accountRepository.save(accountEntity);
    }

    @Override
    public JwtResponse authenticate(final String username, final String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (final DisabledException e) {
            throw new UserDisabledException(USER_DISABLED);
        } catch (final BadCredentialsException e) {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS);
        }

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new JwtResponse(jwtTokenUtils.generateToken(userDetails), jwtTokenUtils.generateRefreshToken(userDetails));
    }
}
