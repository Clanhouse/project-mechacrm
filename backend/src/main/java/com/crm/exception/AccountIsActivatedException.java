package com.crm.exception;

public class AccountIsActivatedException extends RuntimeException {

    public AccountIsActivatedException(String message) {
        super(message);
    }
}
