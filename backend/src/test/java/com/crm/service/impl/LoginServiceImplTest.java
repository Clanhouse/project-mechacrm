package com.crm.service.impl;

import com.crm.config.JwtTokenUtils;
import com.crm.dto.response.JwtResponse;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
import com.crm.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.Instant;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {
    
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AccountRepository accountRepository;
    
    @Mock
    private JwtTokenUtils jwtTokenUtils;

    @Mock
    private AccountServiceImpl accountService;

    @Mock
    private Authentication auth;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private LoginServiceImpl loginService;

    private AccountEntity accountEntity;

    private static final Long ID = 1L;
    private static final String LOGIN = "Test48";
    private static final String EMAIL = "Test48";
    private static final String PASSWORD = "Test48";
    private static final Boolean IS_ACTIVATED = true;
    private static final Timestamp LAST_FAILED_LOGIN = Timestamp.from(Instant.now());
    private static final Timestamp REGISTRATION_DATE = Timestamp.from(Instant.now());
    private static final Timestamp LAST_SUCCESSFUL_LOGIN = Timestamp.from(Instant.now());
    private static final Integer LOGIN_ATTEMPTS = 1;
    private static final RoleEntity ROLE = RoleEntity.builder().id(1L).name("admin").build();
    private static final String TOKEN = "token";
    private static final String REFRESH_TOKEN = "token";

    @Before
    public void setUp(){
        accountEntity = AccountEntity.builder()
                .id(ID)
                .login(LOGIN)
                .email(EMAIL)
                .password(PASSWORD)
                .isActive(IS_ACTIVATED)
                .lastFailedLogin(LAST_FAILED_LOGIN)
                .lastSuccessfulLogin(LAST_SUCCESSFUL_LOGIN)
                .loginAttempts(LOGIN_ATTEMPTS)
                .registrationDate(REGISTRATION_DATE)
                .role(ROLE)
                .build();
    }

    @Test
    public void shouldCounterEqual_2_OnIncreaseAttemptsCounter() {
        when(accountService.findByLogin(LOGIN)).thenReturn(accountEntity);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);

        loginService.increaseAttemptsCounter(LOGIN);

        assertEquals(accountEntity.getLoginAttempts().longValue(), 2);
        verify(accountService, times(1)).findByLogin(LOGIN);
        verify(accountRepository, times(1)).save(accountEntity);
    }

    @Test
    public void shouldResetCounterToZeroOnResetAttemptsCounter() {
        when(accountService.findByLogin(LOGIN)).thenReturn(accountEntity);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);

        loginService.resetAttemptsCounter(LOGIN);

        assertEquals(accountEntity.getLoginAttempts().longValue(), 0);
        assertThat(accountEntity.getLastFailedLogin(), instanceOf(Timestamp.class));
        verify(accountService, times(1)).findByLogin(LOGIN);
        verify(accountRepository, times(1)).save(accountEntity);
    }

    @Test
    public void shouldReturnJwtResponseOnAuthenticate() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(jwtTokenUtils.generateToken(userDetails)).thenReturn(TOKEN);
        when(jwtTokenUtils.generateRefreshToken(userDetails)).thenReturn(REFRESH_TOKEN);

        JwtResponse jwtResponse = loginService.authenticate(LOGIN, PASSWORD);

        assertEquals(jwtResponse.getToken(), TOKEN);
        assertEquals(jwtResponse.getRefreshToken(), REFRESH_TOKEN);
    }
}
