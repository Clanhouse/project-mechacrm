package com.crm.service;

import com.crm.model.db.AccountEntity;
import com.crm.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class JwtUserDetailsServiceTest {

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void createTestUser() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setLogin("userName");
        accountRepository.save(accountEntity);
    }

    @Test
    void should_return_userName() {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername("userName");

        assertThat(userDetails.getUsername()).isEqualTo("userName");
    }

    @Test
    void should_throw_UsernameNotFoundException() {
        assertThatThrownBy(() -> jwtUserDetailsService.loadUserByUsername("unknown"))
                .isExactlyInstanceOf(UsernameNotFoundException.class)
                .hasMessage("unknown")
                .hasNoCause();
    }

}