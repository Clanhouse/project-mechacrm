package com.crm.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ActivationEmail {

    private String subject;
    private String message;
    private String recipient;
}
