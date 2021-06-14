package com.crm.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String SOURCE_ERROR_MESSAGE = "Unknown source";

    @ExceptionHandler(BindException.class)
    public ErrorResponse handleTypeMismatchException(final BindException e, final HttpServletRequest request) {
        final List<String> errorMessages = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ErrorResponse(HttpStatus.BAD_REQUEST, getSource(request), errorMessages);
    }

    private String getSource(final HttpServletRequest request) {
        try {
            return request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        } catch (final Exception e) {
            return SOURCE_ERROR_MESSAGE;
        }
    }
}
