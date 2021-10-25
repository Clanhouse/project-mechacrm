package com.crm.service.impl;

import com.crm.exception.TokenExpiredException;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
import com.crm.model.db.VerificationTokenEntity;
import com.crm.repository.VerificationTokenRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class VerificationTokeServiceImplTest {

    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @InjectMocks
    private VerificationTokenServiceImpl verificationTokenService;

    private static AccountEntity accountEntity;
    private static VerificationTokenEntity expiredVerificationToken;
    private static VerificationTokenEntity neverExpiredVerificationToken;

    private static final Long ID = 1L;
    private static final String LOGIN = "Test48";
    private static final String EMAIL = "Test48";
    private static final String PASSWORD = "Test48";
    private static final boolean IS_ACTIVATED = false;
    private static final Timestamp LAST_FAILED_LOGIN = Timestamp.from(Instant.now());
    private static final Timestamp REGISTRATION_DATE = Timestamp.from(Instant.now());
    private static final Timestamp LAST_SUCCESSFUL_LOGIN = Timestamp.from(Instant.now());
    private static final int LOGIN_ATTEMPTS = 0;
    private static final RoleEntity ROLE = RoleEntity.builder().id(1L).name("user").build();
    private static final String verificationToken = "token";
    private static final LocalDateTime neverExpiredToken = LocalDateTime.of(2050, 1, 1, 1, 1, 1);
    private static final LocalDateTime expiredToken = LocalDateTime.of(2010, 1, 1, 1, 1, 1);

    @BeforeClass
    public static void setUp() {
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

        expiredVerificationToken = VerificationTokenEntity.builder()
                .id(ID)
                .token(verificationToken)
                .user(accountEntity)
                .expiryDate(Timestamp.valueOf(expiredToken))
                .build();

        neverExpiredVerificationToken = VerificationTokenEntity.builder()
                .id(ID)
                .token(verificationToken)
                .user(accountEntity)
                .expiryDate(Timestamp.valueOf(neverExpiredToken))
                .build();
    }

    @Test
    public void shouldReturnTokenOnSaveVerificationTokenByAccountEntity() {
        doNothing().when(verificationTokenRepository).deleteByUserId(ID);
        when(verificationTokenRepository.save(any(VerificationTokenEntity.class))).thenReturn(neverExpiredVerificationToken);

        String token = verificationTokenService.saveVerificationToken(accountEntity);

        assertEquals(token, verificationToken);
        verify(verificationTokenRepository, times(1)).save(any(VerificationTokenEntity.class));
        verify(verificationTokenRepository, times(1)).deleteByUserId(ID);
    }

    @Test
    public void shouldReturnVerificationTokenEntityOnIsTokenExpiredByToken() {
        when(verificationTokenRepository.findByToken(verificationToken)).thenReturn(Optional.of(neverExpiredVerificationToken));

        VerificationTokenEntity vte = verificationTokenService.isTokenNotExpired(verificationToken);

        assertThat(vte.getExpiryDate(), greaterThan(Timestamp.from(Instant.now())));
        verify(verificationTokenRepository, times(1)).findByToken(verificationToken);
    }

    @Test(expected = TokenExpiredException.class)
    public void shouldThrowTokenExpiredExceptionOnIsTokenNonExpiredByExpiredToken() {
        when(verificationTokenRepository.findByToken(verificationToken)).thenReturn(Optional.of(expiredVerificationToken));

        verificationTokenService.isTokenNotExpired(verificationToken);
    }

    @Test(expected = TokenExpiredException.class)
    public void shouldThrowTokenExpiredExceptionOnIsTokenNonExpiredByNotExistedToken() {
        when(verificationTokenRepository.findByToken(verificationToken)).thenThrow(TokenExpiredException.class);

        verificationTokenService.isTokenNotExpired(verificationToken);
    }


}
