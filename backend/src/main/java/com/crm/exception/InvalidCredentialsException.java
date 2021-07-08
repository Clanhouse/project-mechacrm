package com.crm.exception;

public class InvalidCredentialsException extends CrmLoginException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
