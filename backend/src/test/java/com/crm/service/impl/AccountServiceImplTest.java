package com.crm.service.impl;

import com.crm.dto.mapper.AccountMapper;
import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.exception.AccountIsActivatedException;
import com.crm.exception.ElementAlreadyExistException;
import com.crm.exception.TokenExpiredException;
import com.crm.model.db.AccountEntity;
import com.crm.dto.MyEmail;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
    private static AccountResponse accountResponse;
    private static NewAccountRequest newAccountRequest;
    private static MyEmail myEmail;
    private static VerificationTokenEntity neverExpiredVerificationToken;
    private static VerificationTokenEntity expiredExpiredVerificationToken;


    private static final Long id = 1L;
    private static final String login = "Test48";
    private static final String email = "Test48";
    private static final String password = "Test48";
    private static final boolean notActivated = false;
    private static final boolean activated = true;
    private static final Timestamp lastFailedLogin = Timestamp.from(Instant.now());
    private static final Timestamp registrationDate = Timestamp.from(Instant.now());
    private static final Timestamp lastSuccessfulLogin = Timestamp.from(Instant.now());
    private static final int loginAttempts = 1;
    private static final RoleEntity role = RoleEntity.builder().id(1L).name("admin").build();
    private static final String verificationToken = "token";
    private static final LocalDateTime neverExpiredToken = LocalDateTime.of(2050, 1, 1, 1, 1, 1);


    @BeforeClass
    public static void setUp() {
        savedAccountEntity = AccountEntity.builder()
                .id(id)
                .login(login)
                .email(email)
                .password(password)
                .isActivated(notActivated)
                .lastFailedLogin(lastFailedLogin)
                .lastSuccessfulLogin(lastSuccessfulLogin)
                .loginAttempts(loginAttempts)
                .registrationDate(registrationDate)
                .role(role)
                .build();

        accountEntity = AccountEntity.builder()
                .login(login)
                .email(email)
                .password(password)
                .isActivated(notActivated)
                .lastFailedLogin(lastFailedLogin)
                .lastSuccessfulLogin(lastSuccessfulLogin)
                .loginAttempts(loginAttempts)
                .registrationDate(registrationDate)
                .role(role)
                .build();

        activatedAccountEntity = AccountEntity.builder()
                .id(id)
                .login(login)
                .email(email)
                .password(password)
                .isActivated(activated)
                .lastFailedLogin(lastFailedLogin)
                .lastSuccessfulLogin(lastSuccessfulLogin)
                .loginAttempts(loginAttempts)
                .registrationDate(registrationDate)
                .role(role)
                .build();

        accountResponse = AccountResponse.builder()
                .login(login)
                .email(email)
                .build();

        newAccountRequest = NewAccountRequest.builder()
                .login(login)
                .password(password)
                .email(email)
                .build();

        myEmail = MyEmail.builder()
                .recipient("test")
                .message("test")
                .subject("test")
                .build();

        neverExpiredVerificationToken = VerificationTokenEntity.builder()
                .id(id)
                .token(verificationToken)
                .user(activatedAccountEntity)
                .expiryDate(Timestamp.valueOf(neverExpiredToken))
                .build();

        expiredExpiredVerificationToken = VerificationTokenEntity.builder()
                .id(id)
                .token(verificationToken)
                .user(accountEntity)
                .expiryDate(Timestamp.valueOf(neverExpiredToken))
                .build();
    }

    @Test
    public void shouldSaveEntityAndReturnAccountResponse() throws NoSuchElementException {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));
        when(accountRepository.existsByLogin(login)).thenReturn(false);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(savedAccountEntity);
        doNothing().when(emailUtils).sendEmail(savedAccountEntity, verificationToken);
        when(verificationTokenService.saveVerificationToken(savedAccountEntity)).thenReturn(verificationToken);
        when(accountMapper.convertToDto(savedAccountEntity)).thenReturn(accountResponse);

        final AccountResponse accountResponse = accountService.save(newAccountRequest);

        assertEquals(accountResponse.getEmail(), email);
        assertEquals(accountResponse.getLogin(), login);

        verify(roleRepository, times(1)).findById(anyLong());
        verify(accountRepository, times(1)).save(any(AccountEntity.class));
        verify(accountRepository, times(1)).existsByLogin(login);
        verify(emailUtils, times(1)).sendEmail(savedAccountEntity,  verificationToken);
        assertNotNull(accountResponse);
    }

    @Test(expected = ElementAlreadyExistException.class)
    public void shouldThrowAccountExistExceptionOnSave() {
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(role));
        when(accountRepository.existsByLogin(login)).thenReturn(true);

        accountService.save(newAccountRequest);
    }

    @Test
    public void shouldFindAccountById() {
        when(accountRepository.findById(id)).thenReturn(Optional.of(savedAccountEntity));

        AccountEntity accountEntity = accountService.findById(id);

        assertAll(
                () -> assertEquals(id, accountEntity.getId()),
                () -> assertEquals(login, accountEntity.getLogin()),
                () -> assertEquals(password, accountEntity.getPassword()),
                () -> assertEquals(loginAttempts, accountEntity.getLoginAttempts()),
                () -> assertEquals(role, accountEntity.getRole()),
                () -> assertEquals(lastSuccessfulLogin, accountEntity.getLastSuccessfulLogin()),
                () -> assertEquals(lastFailedLogin, accountEntity.getLastFailedLogin()),
                () -> assertEquals(notActivated, accountEntity.getIsActivated()),
                () -> assertEquals(registrationDate, accountEntity.getRegistrationDate())
                );

        verify(accountRepository, times(1)).findById(id);
        assertNotNull(accountEntity);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionOnFindById() {
        when(accountRepository.findById(id)).thenThrow(new NoSuchElementException());

        accountService.findById(id);
    }

    @Test
    public void shouldReturnTrueOnIsAccountExist() {
        when(accountRepository.existsByLogin(login)).thenReturn(true);

        boolean result = accountService.isAccountExist(login);

        assertTrue(result);
        verify(accountRepository, times(1)).existsByLogin(login);
    }

    @Test
    public void shouldReturnFalseOnIsAccountExist() {
        when(accountRepository.existsByLogin(login)).thenReturn(false);

        boolean result = accountService.isAccountExist(login);

        assertFalse(result);
        verify(accountRepository, times(1)).existsByLogin(login);
    }

    @Test
    public void shouldReturnAccountResponseOnActivateNewAccountByToken() {
        when(verificationTokenService.isTokenNotExpired(verificationToken)).thenReturn(neverExpiredVerificationToken);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(savedAccountEntity));
        when(accountRepository.save(savedAccountEntity)).thenReturn(activatedAccountEntity);
        when(accountMapper.convertToDto(activatedAccountEntity)).thenReturn(accountResponse);

        AccountResponse accountResponse = accountService.activateNewAccount(verificationToken);

        assertEquals(accountResponse.getEmail(), email);
        assertEquals(accountResponse.getLogin(), login);

        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(savedAccountEntity);
        verify(verificationTokenService, times(1)).isTokenNotExpired(verificationToken);
        assertNotNull(accountResponse);
    }

    @Test(expected = AccountIsActivatedException.class)
    public void shouldThrowAccountIsActivatedExceptionOnActivateNewAccountByToken() {
        when(verificationTokenService.isTokenNotExpired(verificationToken)).thenReturn(neverExpiredVerificationToken);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(activatedAccountEntity));

        accountService.activateNewAccount(verificationToken);
    }

    @Test(expected = TokenExpiredException.class)
    public void shouldThrowTokenExpiredExceptionOnActivateNewAccountByToken() {
        when(verificationTokenService.isTokenNotExpired(verificationToken)).thenThrow(TokenExpiredException.class);

        accountService.activateNewAccount(verificationToken);
    }

}
