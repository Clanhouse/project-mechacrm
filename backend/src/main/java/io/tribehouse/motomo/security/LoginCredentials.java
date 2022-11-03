package io.tribehouse.motomo.security;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginCredentials {
    @NotNull(message = "Email should not be empty.")
    @Size(max = 100)
    @Email(message = "Email not correct.")
    private String email;
    @NotNull(message = "Password should not be empty.")
    @Size(min = 5, max = 255, message = "Password should have at least 5 characters and less than 255.")
    private String password;
}
