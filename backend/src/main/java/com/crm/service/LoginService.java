package com.crm.service;


import com.crm.dto.response.JwtResponse;

public interface LoginService {

    void increaseAttemptsCounter(final String login);

    void resetAttemptsCounter(final String login);

    JwtResponse authenticate(final String username, final String password);
}
