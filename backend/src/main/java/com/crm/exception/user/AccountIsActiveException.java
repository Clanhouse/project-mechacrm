package com.crm.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AccountIsActiveException extends CrmLoginException {

    public AccountIsActiveException(String message) {
        super(message);
    }
}
