package com.crm.dto.mapper;

import com.crm.dto.request.AccountRequest;
import com.crm.model.db.AccountEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountEntity convertToEntity(final AccountRequest accountRequest) {
        AccountEntity accountEntity = modelMapper.map(accountRequest, AccountEntity.class);
        accountEntity.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
        return accountEntity;
    }

}
