package com.crm.dto.mapper;

import com.crm.dto.response.CustomerResponse;
import com.crm.model.db.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ModelMapper modelMapper;

    public CustomerResponse convertToDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerResponse.class);
    }

    public CustomerEntity convertToEntity(CustomerResponse customerResponse) {
        return modelMapper.map(customerResponse, CustomerEntity.class);
    }
}
