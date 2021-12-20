package com.crm.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.Instant;

import static com.crm.exception.ErrorDict.EMAIL_FORMAT_INVALID;
import static com.crm.exception.ErrorDict.LOGIN_CAN_NOT_BE_EMPTY;
import static com.crm.exception.ErrorDict.LOGIN_FORMAT_INVALID;
import static com.crm.exception.ErrorDict.LOGIN_LENGTH_MUST_BETWEEN;
import static com.crm.exception.ErrorDict.PASSWORD_CAN_NOT_BE_EMPTY;
import static com.crm.exception.ErrorDict.PASSWORD_FORMAT_INVALID;
import static com.crm.exception.ErrorDict.PASSWORD_LENGTH_MUST_BETWEEN;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NewAccountRequest {

    @NotBlank(message = LOGIN_CAN_NOT_BE_EMPTY)
    @Size(min = 2, max = 50, message = LOGIN_LENGTH_MUST_BETWEEN)
    @Pattern(regexp = "^[a-zA-Z0-9_-]{2,50}$", message = LOGIN_FORMAT_INVALID)
    private String login;

    @NotBlank(message = PASSWORD_CAN_NOT_BE_EMPTY)
    @Size(min = 8, max = 50, message = PASSWORD_LENGTH_MUST_BETWEEN)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,50}$", message = PASSWORD_FORMAT_INVALID)
    private String password;

    @Email(message = EMAIL_FORMAT_INVALID)
    private String email;

    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Builder.Default
    private final Timestamp registrationDate = Timestamp.from(Instant.now()); //UTC time zone

}
