package com.crm.service.impl;

import com.crm.config.JwtTokenUtils;
import com.crm.dto.response.JwtResponse;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
import com.crm.repository.AccountRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {
    
    @Mock
    private AuthenticationManager authenticationManager;
    
    @Mock
    private AccountRepository accountRepository;
    
    @Mock
    private JwtTokenUtils jwtTokenUtils;
    
    @InjectMocks
    private LoginServiceImpl loginService;

    private static AccountEntity accountEntity;
    private static Authentication auth;
    private static SecurityContext secCont;
    private static UserDetails userDetails;

    private final Long ID = 1L;
    private final String LOGIN = "Test48";
    private final String EMAIL = "Test48";
    private final String PASSWORD = "Test48";
    private final boolean IS_ACTIVATED = true;
    private final Timestamp LAST_FAILED_LOGIN = Timestamp.from(Instant.now());
    private final Timestamp REGISTRATION_DATE = Timestamp.from(Instant.now());
    private final Timestamp LAST_SUCCESSFUL_LOGIN = Timestamp.from(Instant.now());
    private final int LOGIN_ATTEMPTS = 1;
    private final RoleEntity ROLE = RoleEntity.builder().id(1L).name("admin").build();
    private final String token = "token";
    private final String refreshToken = "token";

    @BeforeClass
    public static void setAuth(){
        auth = Mockito.mock(Authentication.class);
        userDetails = Mockito.mock(UserDetails.class);
    }

    @Before
    public void setUp(){
        accountEntity = AccountEntity.builder()
                .id(ID)
                .login(LOGIN)
                .email(EMAIL)
                .password(PASSWORD)
                .isActivated(IS_ACTIVATED)
                .lastFailedLogin(LAST_FAILED_LOGIN)
                .lastSuccessfulLogin(LAST_SUCCESSFUL_LOGIN)
                .loginAttempts(LOGIN_ATTEMPTS)
                .registrationDate(REGISTRATION_DATE)
                .role(ROLE)
                .build();
    }

    @Test
    public void shouldCounterEqual_2_OnIncreaseAttemptsCounter() {
        when(accountRepository.findByLogin(LOGIN)).thenReturn(Optional.of(accountEntity));
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);

        loginService.increaseAttemptsCounter(LOGIN);

        assertEquals(accountEntity.getLoginAttempts().longValue(), 2);
        verify(accountRepository, times(1)).findByLogin(LOGIN);
        verify(accountRepository, times(1)).save(accountEntity);
    }

    @Test
    public void shouldResetCounterToZeroOnResetAttemptsCounter() {
        when(accountRepository.findByLogin(LOGIN)).thenReturn(Optional.of(accountEntity));
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);

        loginService.resetAttemptsCounter(LOGIN);

        assertEquals(accountEntity.getLoginAttempts().longValue(), 0);
        assertThat(accountEntity.getLastFailedLogin(), instanceOf(Timestamp.class));
        verify(accountRepository, times(1)).findByLogin(LOGIN);
        verify(accountRepository, times(1)).save(accountEntity);
    }

    @Test // todo fix test
    public void shouldReturnJwtResponseOnAuthenticate() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(jwtTokenUtils.generateToken(userDetails)).thenReturn(token);
        when(jwtTokenUtils.generateRefreshToken(userDetails)).thenReturn(token);

        JwtResponse jwtResponse = loginService.authenticate(LOGIN, PASSWORD);

        assertEquals(jwtResponse.getToken(), token);
        assertEquals(jwtResponse.getRefreshToken(), token);
    }
}
