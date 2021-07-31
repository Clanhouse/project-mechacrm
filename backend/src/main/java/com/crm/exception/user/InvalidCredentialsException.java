package com.crm.exception.user;

public class InvalidCredentialsException extends CrmLoginException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
