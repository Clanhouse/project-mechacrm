package com.crm.exception.user;

public class AccountIsActiveException extends CrmLoginException {
    public AccountIsActiveException(String message) {
        super(message);
    }
}
