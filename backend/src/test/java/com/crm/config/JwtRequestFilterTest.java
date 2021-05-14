package com.crm.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtRequestFilterTest {

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    UserDetails userDetails;

    @BeforeEach
    void createUserDetails() {
        this.userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "password";
            }

            @Override
            public String getUsername() {
                return "userName";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    @Test
    void should_username_equal_to_userName_in_token_when_jwt_expiration_token_time_is_1000() {
        String token = jwtTokenUtils.generateToken(userDetails);

        assertThat(jwtTokenUtils.getUsername(token)).isEqualTo("userName");
    }

    @Test
    void should_username_equal_to_userName_in_refreshed_token_when_jwt_expiration_token_time_is_1000() {
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        assertThat(jwtTokenUtils.getUsername(refreshToken)).isEqualTo("userName");
    }

    @Test
    void should_return_true_when_proper_username() {
        String token = jwtTokenUtils.generateToken(userDetails);

        Boolean valid = jwtTokenUtils.isValid(token, userDetails);

        Assertions.assertTrue(valid);
    }

}