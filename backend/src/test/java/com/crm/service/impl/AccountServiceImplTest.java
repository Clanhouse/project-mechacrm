package com.crm.service.impl;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.exception.AccountIsActivatedException;
import com.crm.exception.ElementAlreadyExistException;
import com.crm.exception.ErrorDict;
import com.crm.exception.TokenExpiredException;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
import com.crm.model.db.VerificationTokenEntity;
import com.crm.repository.AccountRepository;
import com.crm.repository.RoleRepository;
import com.crm.util.EmailUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private EmailUtils emailUtils;

    @Mock
    private VerificationTokenServiceImpl verificationTokenService;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountServiceImpl accountService;

    private static AccountEntity accountEntity;
    private static AccountEntity savedAccountEntity;
    private static AccountEntity activatedAccountEntity;
    private static AccountEntity notActivatedAccountEntity;
    private static AccountResponse accountResponse;
    private static NewAccountRequest newAccountRequest;
    private static VerificationTokenEntity neverExpiredVerificationToken;
    private static VerificationTokenEntity expiredVerificationToken;


    private static final Long ID = 10L;
    private static final String LOGIN = "Test485";
    private static final String EMAIL = "Test485";
    private static final String PASSWORD = "Test485";
    private static final boolean NOT_ACTIVATED = false;
    private static final boolean ACTIVATED = true;
    private static final Timestamp LAST_FAILED_LOGIN = Timestamp.from(Instant.now());
    private static final Timestamp REGISTRATION_DATE = Timestamp.from(Instant.now());
    private static final Timestamp LAST_SUCCESSFUL_LOGIN = Timestamp.from(Instant.now());
    private static final int LOGIN_ATTEMPTS = 1;
    private static final RoleEntity ROLE = RoleEntity.builder().id(1L).name("admin").build();
    private static final String VERIFICATION_TOKEN = "tokenToken";
    private static final LocalDateTime NEVER_EXPIRED_TOKEN = LocalDateTime.of(2050, 1, 1, 1, 1, 1);
    private static final LocalDateTime EXPIRED_TOKEN = LocalDateTime.of(2010, 1, 1, 1, 1, 1);
    private static final String USER_ROLE = "ROLE_USER";


    @BeforeClass
    public static void setUp() {
        savedAccountEntity = AccountEntity.builder()
                .id(ID)
                .login(LOGIN)
                .email(EMAIL)
                .password(PASSWORD)
                .isActive(NOT_ACTIVATED)
                .lastFailedLogin(LAST_FAILED_LOGIN)
                .lastSuccessfulLogin(LAST_SUCCESSFUL_LOGIN)
                .loginAttempts(LOGIN_ATTEMPTS)
                .registrationDate(REGISTRATION_DATE)
                .role(ROLE)
                .build();

        notActivatedAccountEntity = AccountEntity.builder()
                .id(ID)
                .login(LOGIN)
                .email(EMAIL)
                .password(PASSWORD)
                .isActive(NOT_ACTIVATED)
                .lastFailedLogin(LAST_FAILED_LOGIN)
                .lastSuccessfulLogin(LAST_SUCCESSFUL_LOGIN)
                .loginAttempts(LOGIN_ATTEMPTS)
                .registrationDate(REGISTRATION_DATE)
                .role(ROLE)
                .build();

        accountEntity = AccountEntity.builder()
                .login(LOGIN)
                .email(EMAIL)
                .password(PASSWORD)
                .isActive(NOT_ACTIVATED)
                .lastFailedLogin(LAST_FAILED_LOGIN)
                .lastSuccessfulLogin(LAST_SUCCESSFUL_LOGIN)
                .loginAttempts(LOGIN_ATTEMPTS)
                .registrationDate(REGISTRATION_DATE)
                .role(ROLE)
                .build();

        activatedAccountEntity = AccountEntity.builder()
                .id(ID)
                .login(LOGIN)
                .email(EMAIL)
                .password(PASSWORD)
                .isActive(ACTIVATED)
                .lastFailedLogin(LAST_FAILED_LOGIN)
                .lastSuccessfulLogin(LAST_SUCCESSFUL_LOGIN)
                .loginAttempts(LOGIN_ATTEMPTS)
                .registrationDate(REGISTRATION_DATE)
                .role(ROLE)
                .build();

        accountResponse = AccountResponse.builder()
                .login(LOGIN)
                .email(EMAIL)
                .build();

        newAccountRequest = NewAccountRequest.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        neverExpiredVerificationToken = VerificationTokenEntity.builder()
                .id(ID)
                .token(VERIFICATION_TOKEN)
                .user(activatedAccountEntity)
                .expiryDate(Timestamp.valueOf(NEVER_EXPIRED_TOKEN))
                .build();

        expiredVerificationToken = VerificationTokenEntity.builder()
                .id(ID)
                .token(VERIFICATION_TOKEN)
                .user(accountEntity)
                .expiryDate(Timestamp.valueOf(EXPIRED_TOKEN))
                .build();
    }

    @Test
    public void shouldSaveEntityAndReturnAccountResponse() throws NoSuchElementException {
        when(roleRepository.findByName(USER_ROLE)).thenReturn(Optional.of(ROLE));
        when(accountRepository.existsByLogin(LOGIN)).thenReturn(false);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(savedAccountEntity);
        doNothing().when(emailUtils).sendEmail(savedAccountEntity, VERIFICATION_TOKEN);
        when(verificationTokenService.saveVerificationToken(savedAccountEntity)).thenReturn(VERIFICATION_TOKEN);

        accountService.save(newAccountRequest);

        verify(roleRepository, times(1)).findByName(USER_ROLE);
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
        verify(accountRepository, times(1)).existsByLogin(LOGIN);
        verify(emailUtils, times(1)).sendEmail(savedAccountEntity, VERIFICATION_TOKEN);
        assertNotNull(accountResponse);
    }

    @Test
    public void shouldThrowAccountExistExceptionOnSaveByEmail() {
        when(roleRepository.findByName(USER_ROLE)).thenReturn(Optional.of(ROLE));
        when(accountRepository.existsByEmail(EMAIL)).thenReturn(true);

        assertThatThrownBy(() -> accountService.save(newAccountRequest))
                .isInstanceOf(ElementAlreadyExistException.class)
                .hasMessage(ErrorDict.ACCOUNT_ALREADY_EXIST_BY_EMAIL);

        verify(accountRepository, times(0)).save(any());
        verify(accountRepository, times(1)).existsByEmail(any());
        verify(accountRepository, times(0)).existsByLogin(any());
        verify(roleRepository, times(1)).findByName(USER_ROLE);
        verify(verificationTokenService, times(0)).saveVerificationToken(any());
    }

    @Test
    public void shouldThrowAccountExistExceptionOnSaveByLogin() {
        when(roleRepository.findByName(USER_ROLE)).thenReturn(Optional.of(ROLE));
        when(accountRepository.existsByEmail(EMAIL)).thenReturn(false);
        when(accountRepository.existsByLogin(LOGIN)).thenReturn(true);

        assertThatThrownBy(() -> accountService.save(newAccountRequest))
                .isInstanceOf(ElementAlreadyExistException.class)
                .hasMessage(ErrorDict.ACCOUNT_ALREADY_EXIST_BY_LOGIN);

        verify(accountRepository, times(0)).save(any());
        verify(roleRepository, times(1)).findByName(USER_ROLE);
        verify(accountRepository, times(1)).existsByEmail(any());
        verify(accountRepository, times(1)).existsByLogin(any());
        verify(verificationTokenService, times(0)).saveVerificationToken(any());
    }

    @Test
    public void shouldFindAccountById() {
        when(accountRepository.findById(ID)).thenReturn(Optional.of(savedAccountEntity));

        AccountEntity accountEntity = accountService.findById(ID);

        assertAll(
                () -> assertEquals(ID, accountEntity.getId()),
                () -> assertEquals(LOGIN, accountEntity.getLogin()),
                () -> assertEquals(PASSWORD, accountEntity.getPassword()),
                () -> assertEquals(LOGIN_ATTEMPTS, accountEntity.getLoginAttempts()),
                () -> assertEquals(ROLE, accountEntity.getRole()),
                () -> assertEquals(LAST_SUCCESSFUL_LOGIN, accountEntity.getLastSuccessfulLogin()),
                () -> assertEquals(LAST_FAILED_LOGIN, accountEntity.getLastFailedLogin()),
                () -> assertEquals(NOT_ACTIVATED, accountEntity.getIsActive()),
                () -> assertEquals(REGISTRATION_DATE, accountEntity.getRegistrationDate())
        );

        verify(accountRepository, times(1)).findById(ID);
        assertNotNull(accountEntity);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionOnFindById() {
        assertThatThrownBy(() -> accountService.findById(ID))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(ErrorDict.ACCOUNT_DOES_NOT_EXIST);

        verify(accountRepository, times(1)).findById(ID);
    }

    @Test
    public void shouldFindAccountByLogin() {
        when(accountRepository.findByLogin(LOGIN)).thenReturn(Optional.of(savedAccountEntity));

        AccountEntity accountEntity = accountService.findByLogin(LOGIN);

        assertAll(
                () -> assertEquals(ID, accountEntity.getId()),
                () -> assertEquals(LOGIN, accountEntity.getLogin()),
                () -> assertEquals(PASSWORD, accountEntity.getPassword()),
                () -> assertEquals(LOGIN_ATTEMPTS, accountEntity.getLoginAttempts()),
                () -> assertEquals(ROLE, accountEntity.getRole()),
                () -> assertEquals(LAST_SUCCESSFUL_LOGIN, accountEntity.getLastSuccessfulLogin()),
                () -> assertEquals(LAST_FAILED_LOGIN, accountEntity.getLastFailedLogin()),
                () -> assertEquals(NOT_ACTIVATED, accountEntity.getIsActive()),
                () -> assertEquals(REGISTRATION_DATE, accountEntity.getRegistrationDate())
        );

        verify(accountRepository, times(1)).findByLogin(LOGIN);
        assertNotNull(accountEntity);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionOnFindByLogin() {
        assertThatThrownBy(() -> accountService.findByLogin("WwWwWwW098"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(ErrorDict.ACCOUNT_DOES_NOT_EXIST);

        verify(accountRepository, times(1)).findByLogin("WwWwWwW098");
    }

    @Test
    public void shouldReturnTrueOnIsAccountExist() {
        when(accountRepository.existsByLogin(LOGIN)).thenReturn(true);

        boolean result = accountService.accountExistsByLogin(LOGIN);

        assertTrue(result);
        verify(accountRepository, times(1)).existsByLogin(LOGIN);
    }

    @Test
    public void shouldReturnFalseOnIsAccountExist() {
        when(accountRepository.existsByLogin(LOGIN)).thenReturn(false);

        boolean result = accountService.accountExistsByLogin(LOGIN);

        assertFalse(result);
        verify(accountRepository, times(1)).existsByLogin(LOGIN);
    }

    @Test
    @Transactional
    @Rollback
    public void shouldReturnAccountResponseOnActivateNewAccountByToken() {
        when(verificationTokenService.getVerificationToken(VERIFICATION_TOKEN)).thenReturn(neverExpiredVerificationToken);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(notActivatedAccountEntity));
        when(accountRepository.save(notActivatedAccountEntity)).thenReturn(activatedAccountEntity);
        when(accountMapper.convertToDto(activatedAccountEntity)).thenReturn(accountResponse);

        AccountResponse accountResponse = accountService.activateAccount(VERIFICATION_TOKEN);

        assertEquals(accountResponse.getEmail(), EMAIL);
        assertEquals(accountResponse.getLogin(), LOGIN);

        verify(accountRepository, times(1)).findById(ID);
        verify(accountRepository, times(1)).save(notActivatedAccountEntity);
        verify(verificationTokenService, times(1)).getVerificationToken(VERIFICATION_TOKEN);
        assertNotNull(accountResponse);
    }

    @Test
    public void shouldThrowAccountIsActivatedExceptionOnActivateNewAccountByToken() {
        when(verificationTokenService.getVerificationToken(VERIFICATION_TOKEN)).thenReturn(neverExpiredVerificationToken);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(activatedAccountEntity));

        assertThatThrownBy(() -> accountService.activateAccount(VERIFICATION_TOKEN))
                .isInstanceOf(AccountIsActivatedException.class)
                .hasMessage(ErrorDict.ACCOUNT_IS_ACTIVE);

        verify(verificationTokenService, times(1)).getVerificationToken(VERIFICATION_TOKEN);
        verify(accountRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldThrowTokenExpiredExceptionOnActivateNewAccountByToken() {
        when(verificationTokenService.getVerificationToken(VERIFICATION_TOKEN)).thenThrow(new TokenExpiredException(ErrorDict.TOKEN_EXPIRED));

        assertThatThrownBy(() -> accountService.activateAccount(VERIFICATION_TOKEN))
                .isInstanceOf(TokenExpiredException.class)
                .hasMessage(ErrorDict.TOKEN_EXPIRED);

        verify(accountRepository, times(0)).findById(ID);
        verify(accountRepository, times(0)).save(any());
        verify(accountMapper, times(0)).convertToDto(any());
    }

}
