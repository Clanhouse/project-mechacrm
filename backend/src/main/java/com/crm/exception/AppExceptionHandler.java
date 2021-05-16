package com.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AppExceptionHandler {

    public static final String SOURCE_ERROR_MESSAGE = "Unknown source";

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (final Exception exc, final HttpServletRequest request){

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),getSource(request),-1, exc.getMessage()),
        HttpStatus.BAD_REQUEST);
    }

    private String getSource(final HttpServletRequest request) {
        String source;
        try {
            source = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        } catch (Exception e) {
            source = SOURCE_ERROR_MESSAGE;
        }
        return source;
    }
}
