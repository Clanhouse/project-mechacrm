package com.crm.service;

public interface LoginService {

    void increaseAttemptsCounter(String login);

    void resetAttemptsCounter(String login);

}
