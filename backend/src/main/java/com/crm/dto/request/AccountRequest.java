package com.crm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    private String login;
    private String password;
    private String email;
}
