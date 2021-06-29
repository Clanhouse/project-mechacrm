package com.crm.dto.mapper;

import com.crm.dto.request.NewAccountRequest;
import com.crm.model.db.AccountEntity;
import com.crm.model.db.RoleEntity;
import com.crm.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountMapperTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository repository;

    @InjectMocks
    private AccountMapper accountMapper;

    @Test
    public void returnAccountEntity() {

        RoleEntity test_role = RoleEntity.builder()
                .id(1L)
                .name("TEST ROLE")
                .build();

        NewAccountRequest newAccountRequest = NewAccountRequest.builder()
                .email("TEST@EMAIL.COM")
                .login("TEST LOGIN")
                .password("TESTPASSWORD")
                .build();

        when(passwordEncoder.encode(any())).thenReturn("ENCODED PASSWORD");
        when(repository.findById(2L)).thenReturn(Optional.of(test_role));

        AccountEntity accountEntity = accountMapper.convertToEntity(newAccountRequest);

        assertThat(accountEntity.getLogin()).isEqualTo(newAccountRequest.getLogin());
        assertThat(accountEntity.getLoginAttempts()).isEqualTo(0);
        assertThat(accountEntity.getPassword()).isEqualTo("ENCODED PASSWORD");
        assertThat(accountEntity.getRegistrationDate()).isEqualTo(newAccountRequest.getRegistrationDate());
        assertThat(accountEntity.getRole()).isEqualTo(test_role);

    }
}
