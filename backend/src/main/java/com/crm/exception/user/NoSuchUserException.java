package com.crm.exception.user;

public class NoSuchUserException extends CrmLoginException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
