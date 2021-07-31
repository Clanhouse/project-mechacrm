package com.crm.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchAccountException extends CrmLoginException {

    public NoSuchAccountException(String message) {
        super(message);
    }
}
