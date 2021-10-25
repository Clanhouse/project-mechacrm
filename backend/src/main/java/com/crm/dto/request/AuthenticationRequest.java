package com.crm.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.crm.exception.ErrorDict.LOGIN_CAN_NOT_BE_EMPTY;
import static com.crm.exception.ErrorDict.PASSWORD_CAN_NOT_BE_EMPTY;

@Getter
@Setter
public class AuthenticationRequest {

    @NotBlank(message = LOGIN_CAN_NOT_BE_EMPTY)
    private String login;

    @NotBlank(message = PASSWORD_CAN_NOT_BE_EMPTY)
    private String password;
}
