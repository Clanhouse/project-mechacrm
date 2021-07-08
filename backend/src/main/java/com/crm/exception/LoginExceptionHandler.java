package com.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestControllerAdvice
public class LoginExceptionHandler {

    private static final String SOURCE_ERROR_MESSAGE = "Unknown source";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CrmLoginException.class)
    public ErrorResponse handleTypeMismatchException(final CrmLoginException e, final HttpServletRequest request) {
        return new ErrorResponse(getSource(request), Collections.singletonList(e.getMessage()));
    }

    private String getSource(final HttpServletRequest request) {
        try {
            return request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        } catch (final Exception e) {
            return SOURCE_ERROR_MESSAGE;
        }
    }
}
