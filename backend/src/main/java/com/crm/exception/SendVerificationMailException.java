package com.crm.exception;

public class SendVerificationMailException extends RuntimeException{
    public SendVerificationMailException(String message){
        super(message);
    }
}
