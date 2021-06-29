package com.crm.dto.mapper;

import com.crm.dto.request.NewAccountRequest;
import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountEntity;
import com.crm.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository repository;

    public AccountResponse convertToDto(final AccountEntity accountEntity) {
        return AccountResponse.builder()
                .id(accountEntity.getId())
                .login(accountEntity.getLogin())
                .build();
    }

    public AccountEntity convertToEntity(final NewAccountRequest newAccountRequest) {
        return AccountEntity.builder()
                .email(newAccountRequest.getEmail())
                .login(newAccountRequest.getLogin())
                .password(passwordEncoder.encode(newAccountRequest.getPassword()))
                .registrationDate(newAccountRequest.getRegistrationDate())
                .role((repository.findById(2L)).orElseThrow(NoSuchElementException::new))
                .loginAttempts(0)
                .build();
    }
}
