package com.crm.service;

public interface LoginService {

    void increaseAttemptsCounter(final String login);

    void resetAttemptsCounter(final String login);

    void authenticate(final String username, final String password);

}
