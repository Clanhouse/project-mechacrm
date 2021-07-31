package com.crm.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AccountAlreadyExistException extends CrmLoginException {

    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
