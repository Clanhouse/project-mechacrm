package com.crm.exception;

public class UserDisabledException extends CrmLoginException {
    public UserDisabledException(String message) {
        super(message);
    }
}
