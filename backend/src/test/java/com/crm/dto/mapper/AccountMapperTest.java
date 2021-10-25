package com.crm.dto.mapper;

import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountMapperTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private AccountMapper accountMapper;

    private static AccountEntity accountEntity;
    private static NewAccountRequest newAccountRequest;

    private static final Long ID = 1L;
    private static final String LOGIN = "Test48";
    private static final String EMAIL = "Test48";
    private static final String PASSWORD = "Test48";
    private static final boolean IS_ACTIVATED = true;
    private static final Timestamp LAST_FAILED_LOGIN = Timestamp.from(Instant.now());
    private static final Timestamp REGISTRATION_DATE = Timestamp.from(Instant.now());
    private static final Timestamp LAST_SUCCESSFUL_LOGIN = Timestamp.from(Instant.now());
    private static final int LOGIN_ATTEMPTS = 1;
    private static final RoleEntity ROLE = RoleEntity.builder().id(1L).name("admin").build();

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

        newAccountRequest = NewAccountRequest.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .email(EMAIL)
                .build();
    }

    @Test
    public void shouldReturnMappedAccountEntityToAccountResponse() {
        final AccountResponse accountResponse = accountMapper.convertToDto(accountEntity);

        assertAll(
                () -> assertEquals(EMAIL, accountResponse.getEmail()),
                () -> assertEquals(LOGIN, accountResponse.getLogin())
        );

        verify(modelMapper, times(1)).map(accountEntity, AccountResponse.class);
    }
}
