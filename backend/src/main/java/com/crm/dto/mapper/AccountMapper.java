package com.crm.dto.mapper;

import com.crm.dto.response.AccountResponse;
import com.crm.model.db.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountResponse convertToDto(final AccountEntity accountEntity) {
        return modelMapper.map(accountEntity, AccountResponse.class);
    }
}
