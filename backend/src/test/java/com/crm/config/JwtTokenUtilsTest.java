package com.crm.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JwtTokenUtilsTest {

    private static final String SECRET = "tempToken";
    private static final String TOKEN_EXPIRATION_TIME = "1000";
    private static final String REFRESH_TOKEN_EXPIRATION_TIME = "600";

    @Mock
    private UserDetails userDetails;

    @Test
    public void shouldReturnNotEmptyToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        jwtTokenUtils.setRefreshTokenExpirationTime(REFRESH_TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setSecret(SECRET);

        String token = jwtTokenUtils.generateToken(userDetails);

        assertThat(token).isNotBlank().isNotEmpty();
    }

    @Test
    public void shouldReturnNotEmptyRefreshedToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        jwtTokenUtils.setRefreshTokenExpirationTime(REFRESH_TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setSecret(SECRET);

        String token = jwtTokenUtils.generateRefreshToken(userDetails);

        assertThat(token).isNotBlank().isNotEmpty();
    }

    @Test
    public void shouldReturnUserNameEqualToExample() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        jwtTokenUtils.setRefreshTokenExpirationTime(REFRESH_TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setSecret(SECRET);

        when(userDetails.getUsername()).thenReturn("example");
        String token = jwtTokenUtils.generateToken(userDetails);
        String userName = jwtTokenUtils.getUsername(token);

        assertThat(userName).isEqualTo("example");
    }

    @Test
    public void shouldReturnExpirationDateEqualToTOKEN_EXPIRATION_TIME() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        jwtTokenUtils.setRefreshTokenExpirationTime(REFRESH_TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setSecret(SECRET);

        when(userDetails.getUsername()).thenReturn("example");
        String token = jwtTokenUtils.generateToken(userDetails);
        Boolean valid = jwtTokenUtils.isValid(token, userDetails);

        assertTrue(valid);
    }
}