package com.crm.dto.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.crm.exception.ErrorDict.EMAIL_FORMAT_INVALID;
import static com.crm.exception.ErrorDict.LOGIN_CAN_NOT_BE_EMPTY;
import static com.crm.exception.ErrorDict.LOGIN_LENGTH_CAN_NOT_BE_GREATER_THAN;
import static com.crm.exception.ErrorDict.PASSWORD_CAN_NOT_BE_EMPTY;
import static com.crm.exception.ErrorDict.PASSWORD_LENGTH_MUST_BETWEEN;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NewAccountRequest {

    @NotBlank(message = LOGIN_CAN_NOT_BE_EMPTY)
    @Size(max = 50, message = LOGIN_LENGTH_CAN_NOT_BE_GREATER_THAN)
    private String login;

    @NotBlank(message = PASSWORD_CAN_NOT_BE_EMPTY)
    @Size(min = 8, max = 30, message = PASSWORD_LENGTH_MUST_BETWEEN)
    private String password;

    @Email(message = EMAIL_FORMAT_INVALID)
    private String email;

    @ReadOnlyProperty
    @ApiModelProperty(hidden = true)
    private final Timestamp registrationDate = Timestamp.valueOf(LocalDateTime.now());
}

