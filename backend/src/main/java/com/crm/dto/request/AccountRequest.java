package com.crm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotNull(message = "Nazwa użytkownika nie może być pusta")
    @NotBlank(message = "nazwa użytkownika nie może być pusta")
    @Size(max = 50, message = "nazwa użytkownika nie może być dłuższa niż 50 znaków")
    private String login;
    @NotNull(message = "Hasło nie może być puste")
    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 8, max = 30, message = "Hasło powinno zawierać się w przedziale od 8 do 15 znaków")
    private String password;

    public void setLogin(String login) {
        this.login = login.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }
}
