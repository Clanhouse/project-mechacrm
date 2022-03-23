package com.crm.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.crm.exception.ErrorDict.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    @NotBlank(message = LOGIN_CAN_NOT_BE_EMPTY)
    private String login;

    @NotBlank(message = PASSWORD_CAN_NOT_BE_EMPTY)
    private String password;
}
