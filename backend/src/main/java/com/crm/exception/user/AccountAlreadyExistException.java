package com.crm.exception.user;

public class AccountAlreadyExistException extends CrmLoginException {

    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
