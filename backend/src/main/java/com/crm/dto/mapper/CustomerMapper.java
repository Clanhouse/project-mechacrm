package com.crm.dto.mapper;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.model.db.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ModelMapper modelMapper;

    public CustomerResponse convertToDto(final CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerResponse.class);
    }

    public CustomerEntity convertToEntity(final CustomerRequest customerRequest) {
        return modelMapper.map(customerRequest, CustomerEntity.class);
    }

    public CustomerEntity updateProperties(final CustomerEntity customerEntity, final CustomerRequest customerRequest) {
        customerEntity.setName(customerRequest.getName());
        customerEntity.setSurname(customerRequest.getSurname());
        customerEntity.setPhone(customerRequest.getPhone());
        customerEntity.setAddress(customerRequest.getAddress());

        return customerEntity;
    }
}
