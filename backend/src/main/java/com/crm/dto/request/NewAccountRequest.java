package com.crm.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewAccountRequest {

    @NotNull(message = "Nazwa użytkownika nie może być pusta")
    @NotBlank(message = "nazwa użytkownika nie może być pusta")
    @Size(max = 50, message = "nazwa użytkownika nie może być dłuższa niż 50 znaków")
    private String login;
    @NotNull(message = "Hasło nie może być puste")
    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 8, max = 30, message = "Hasło powinno zawierać się w przedziale od 8 do 15 znaków")
    private String password;
    @Email(message = "Zły format e-maila")
    @NotNull
    private String email;

    private final Timestamp registrationDate = Timestamp.valueOf(LocalDateTime.now());


    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setLogin(String login) {
        this.login = login.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }
}

