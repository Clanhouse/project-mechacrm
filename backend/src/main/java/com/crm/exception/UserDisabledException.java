package com.crm.exception;

import com.crm.exception.user.CrmLoginException;

public class UserDisabledException extends CrmLoginException {

    public UserDisabledException(String message) {
        super(message);
    }
}
