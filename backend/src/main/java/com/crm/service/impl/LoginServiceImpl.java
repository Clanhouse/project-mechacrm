package com.crm.service.impl;

import com.crm.repository.AccountRepository;
import com.crm.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AccountRepository accountRepository;

    @Override
    public void increaseAttemptsCounter(String login) {
        accountRepository.findByLogin(login).orElseThrow(()->new NoSuchElementException("Użytkownik taki nie istnieje"));
    }

    @Override
    public void resetAttemptsCounter(String login) {

    }
}
