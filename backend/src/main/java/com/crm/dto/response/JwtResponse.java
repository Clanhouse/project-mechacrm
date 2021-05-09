package com.crm.dto.response;

import lombok.Getter;

@Getter
public class JwtResponse {

    private final String token;
    private final String refreshToken;

    public JwtResponse(final String token, final String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
