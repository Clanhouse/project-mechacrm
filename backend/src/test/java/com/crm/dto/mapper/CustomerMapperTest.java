package com.crm.dto.mapper;

import com.crm.dto.response.CustomerResponse;
import com.crm.model.db.CustomerEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerMapperTest {

    @Spy
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

    @Before
    public void setUp() {
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
        final CustomerResponse customerResponse = customerMapper.convertToDto(customerEntity);

        assertEquals(ID, customerResponse.getId());
        assertEquals(NAME, customerResponse.getName());
        assertEquals(SURNAME, customerResponse.getSurname());
        assertEquals(PHONE, customerResponse.getPhone());
        assertEquals(ADDRESS, customerResponse.getAddress());
        verify(modelMapper, times(1)).map(customerEntity, CustomerResponse.class);
    }

    @Test
    public void shouldReturnMappedCustomerDtoToCustomer() {
        final CustomerEntity customerEntity = customerMapper.convertToEntity(customerResponse);

        assertEquals(ID, customerEntity.getId());
        assertEquals(NAME, customerEntity.getName());
        assertEquals(SURNAME, customerEntity.getSurname());
        assertEquals(PHONE, customerEntity.getPhone());
        assertEquals(ADDRESS, customerEntity.getAddress());
        verify(modelMapper, times(1)).map(customerResponse, CustomerEntity.class);
    }

}
