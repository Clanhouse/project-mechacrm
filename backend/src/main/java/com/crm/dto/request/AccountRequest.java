package com.crm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.crm.exception.ErrorDict.LOGIN_CAN_NOT_BE_EMPTY;
import static com.crm.exception.ErrorDict.LOGIN_LENGTH_CAN_NOT_BE_GREATER_THAN;
import static com.crm.exception.ErrorDict.PASSWORD_CAN_NOT_BE_EMPTY;
import static com.crm.exception.ErrorDict.PASSWORD_LENGTH_MUST_BETWEEN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotNull(message = LOGIN_CAN_NOT_BE_EMPTY)
    @NotBlank(message = LOGIN_CAN_NOT_BE_EMPTY)
    @Size(max = 50, message = LOGIN_LENGTH_CAN_NOT_BE_GREATER_THAN)
    private String login;
    @NotNull(message = PASSWORD_CAN_NOT_BE_EMPTY)
    @NotBlank(message = PASSWORD_CAN_NOT_BE_EMPTY)
    @Size(min = 8, max = 30, message = PASSWORD_LENGTH_MUST_BETWEEN)
    private String password;
}
