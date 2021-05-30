package com.crm.dto;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.response.CustomerResponse;
import com.crm.model.db.CustomerEntity;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerMapperTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerMapper customerMapper;

    private CustomerResponse customerResponse;
    private CustomerEntity customerEntity;
    private final Long ID = 1L;
    private final String NAME = "test";
    private final String SURNAME = "test";
    private final String PHONE = "+48 111111111";
    private final String ADDRESS = "test";

    @BeforeAll
    void setUp() {
        customerResponse = CustomerResponse.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .build();

        customerEntity = CustomerEntity.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .cars(new HashSet<>())
                .build();
    }

    @Test
    public void shouldReturnMappedCustomerToCustomerDto() {
        Mockito.when(customerMapper.convertToDto(any(CustomerEntity.class))).thenReturn(eq(customerResponse));
        CustomerResponse customerResponse = customerMapper.convertToDto(eq(this.customerEntity));
        assertEquals(eq(ID), eq(customerResponse.getId()));
        assertEquals(NAME, customerResponse.getName());
        assertEquals(SURNAME, customerResponse.getSurname());
        assertEquals(PHONE, customerResponse.getPhone());
        assertEquals(ADDRESS, customerResponse.getAddress());
    }

    @Test
    public void shouldReturnMappedCustomerDtoToCustomer() {
        Mockito.when(customerMapper.convertToEntity(any(CustomerResponse.class))).thenReturn(customerEntity);
        CustomerEntity customerEntity = customerMapper.convertToEntity(this.customerResponse);
        assertEquals(ID, customerEntity.getId());
        assertEquals(NAME, customerEntity.getName());
        assertEquals(SURNAME, customerEntity.getSurname());
        assertEquals(PHONE, customerEntity.getPhone());
        assertEquals(ADDRESS, customerEntity.getAddress());
    }

}
