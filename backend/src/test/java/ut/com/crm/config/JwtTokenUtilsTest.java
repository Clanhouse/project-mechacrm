package ut.com.crm.config;

import com.crm.config.JwtTokenUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilsTest {

    private static final String SECRET = "tempToken";
    private static final String TOKEN_EXPIRATION_TIME = "1000";
    private static final String REFRESH_TOKEN_EXPIRATION_TIME = "600";

    @Mock
    private UserDetails userDetails;

    @Test
    void shouldReturnNotEmptyToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        jwtTokenUtils.setRefreshTokenExpirationTime(REFRESH_TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setSecret(SECRET);

        String token = jwtTokenUtils.generateToken(userDetails);

        assertThat(token).isNotBlank().isNotEmpty();
    }

    @Test
    void shouldReturnNotEmptyRefreshedToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        jwtTokenUtils.setRefreshTokenExpirationTime(REFRESH_TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setSecret(SECRET);

        String token = jwtTokenUtils.generateRefreshToken(userDetails);

        assertThat(token).isNotBlank().isNotEmpty();
    }

    @Test
    void shouldReturnUserNameEqualToExample() {

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
    void shouldReturnExpirationDateEqualToTOKEN_EXPIRATION_TIME() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        jwtTokenUtils.setRefreshTokenExpirationTime(REFRESH_TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setTokenExpirationTime(TOKEN_EXPIRATION_TIME);
        jwtTokenUtils.setSecret(SECRET);

        when(userDetails.getUsername()).thenReturn("example");
        String token = jwtTokenUtils.generateToken(userDetails);
        Boolean valid = jwtTokenUtils.isValid(token, userDetails);

        Assertions.assertTrue(valid);
    }
}