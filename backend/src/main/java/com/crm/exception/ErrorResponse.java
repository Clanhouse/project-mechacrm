package com.crm.exception;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private Integer status;
    private String source;
    private Integer code;
    private String message;
}
