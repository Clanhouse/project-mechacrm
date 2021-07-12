package com.crm.exception.user;

public class NoSuchAccountException extends CrmLoginException {

    public NoSuchAccountException(String message) {
        super(message);
    }
}
