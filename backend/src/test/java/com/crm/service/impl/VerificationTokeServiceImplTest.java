package com.crm.service.impl;

import com.crm.exception.ErrorDict;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
    private static final Boolean IS_ACTIVATED = false;
    private static final Timestamp LAST_FAILED_LOGIN = Timestamp.from(Instant.now());
    private static final Timestamp REGISTRATION_DATE = Timestamp.from(Instant.now());
    private static final Timestamp LAST_SUCCESSFUL_LOGIN = Timestamp.from(Instant.now());
    private static final Integer LOGIN_ATTEMPTS = 0;
    private static final RoleEntity ROLE = RoleEntity.builder().id(1L).name("user").build();
    private static final String VERIFICATION_TOKEN = "token";
    private static final LocalDateTime NEVER_EXPIRED_TOKEN = LocalDateTime.of(2050, 1, 1, 1, 1, 1);
    private static final LocalDateTime EXPIRED_TOKEN = LocalDateTime.of(2010, 1, 1, 1, 1, 1);

    @BeforeClass
    public static void setUp() {
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

        expiredVerificationToken = VerificationTokenEntity.builder()
                .id(ID)
                .token(VERIFICATION_TOKEN)
                .user(accountEntity)
                .expiryDate(Timestamp.valueOf(EXPIRED_TOKEN))
                .build();

        neverExpiredVerificationToken = VerificationTokenEntity.builder()
                .id(ID)
                .token(VERIFICATION_TOKEN)
                .user(accountEntity)
                .expiryDate(Timestamp.valueOf(NEVER_EXPIRED_TOKEN))
                .build();
    }

    @Test
    public void shouldReturnVerificationTokenOnSaveVerificationTokenByAccountEntity() {
        doNothing().when(verificationTokenRepository).deleteByUserId(ID);
        when(verificationTokenRepository.save(any(VerificationTokenEntity.class))).thenReturn(neverExpiredVerificationToken);

        String token = verificationTokenService.saveVerificationToken(accountEntity);

        assertEquals(token, VERIFICATION_TOKEN);
        verify(verificationTokenRepository, times(1)).save(any(VerificationTokenEntity.class));
        verify(verificationTokenRepository, times(1)).deleteByUserId(ID);
    }

    @Test
    public void shouldReturnVerificationTokenEntityOnIsTokenExpiredByToken() {
        when(verificationTokenRepository.findByToken(VERIFICATION_TOKEN)).thenReturn(Optional.of(neverExpiredVerificationToken));

        VerificationTokenEntity vte = verificationTokenService.getVerificationToken(VERIFICATION_TOKEN);

        assertThat(vte.getExpiryDate(), greaterThan(Timestamp.from(Instant.now())));
        verify(verificationTokenRepository, times(1)).findByToken(VERIFICATION_TOKEN);
    }

    @Test
    public void shouldThrowTokenExpiredExceptionOnIsTokenNonExpiredByExpiredToken() {
        when(verificationTokenRepository.findByToken(VERIFICATION_TOKEN)).thenReturn(Optional.of(expiredVerificationToken));

        assertThatThrownBy(() -> verificationTokenService.getVerificationToken(VERIFICATION_TOKEN))
                .isInstanceOf(TokenExpiredException.class)
                .hasMessage(ErrorDict.TOKEN_EXPIRED);
    }

    @Test
    public void shouldThrowTokenExpiredExceptionOnIsTokenNonExpiredByNotExistedToken() {
        when(verificationTokenRepository.findByToken(VERIFICATION_TOKEN)).thenThrow(new TokenExpiredException(ErrorDict.TOKEN_EXPIRED));

        assertThatThrownBy(() -> verificationTokenService.getVerificationToken(VERIFICATION_TOKEN))
                .isInstanceOf(TokenExpiredException.class)
                .hasMessage(ErrorDict.TOKEN_EXPIRED);
    }


}
