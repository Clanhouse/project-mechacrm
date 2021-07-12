package com.crm.dto.mapper;

import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse convertToDto(final AccountEntity accountEntity) {
        return AccountResponse.builder()
                .id(accountEntity.getId())
                .login(accountEntity.getLogin())
                .build();
    }
}
