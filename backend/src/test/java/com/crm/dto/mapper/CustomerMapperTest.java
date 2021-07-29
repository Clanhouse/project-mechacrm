package com.crm.dto.mapper;

import com.crm.dto.request.CustomerRequest;
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

import static org.junit.jupiter.api.Assertions.assertAll;
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

    private CustomerRequest customerRequest;
    private CustomerEntity customerEntity;

    private static final Long ID = 1L;
    private static final String NAME = "test";
    private static final String SURNAME = "test";
    private static final String PHONE = "+48 111111111";
    private static final String ADDRESS = "test";

    @Before
    public void setUp() {
        customerRequest = CustomerRequest.builder()
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
    public void shouldReturnMappedCustomerEntityToCustomerResponse() {
        final CustomerResponse customerResponse = customerMapper.convertToDto(customerEntity);

        assertAll(
                () -> assertEquals(ID, customerResponse.getId()),
                () -> assertEquals(NAME, customerResponse.getName()),
                () -> assertEquals(SURNAME, customerResponse.getSurname()),
                () -> assertEquals(PHONE, customerResponse.getPhone()),
                () -> assertEquals(ADDRESS, customerResponse.getAddress())
        );

        verify(modelMapper, times(1)).map(customerEntity, CustomerResponse.class);
    }

    @Test
    public void shouldReturnMappedCustomerRequestToCustomerEntity() {
        final CustomerEntity customerEntity = customerMapper.convertToEntity(customerRequest);

        assertAll(
                () -> assertEquals(ID, customerEntity.getId()),
                () -> assertEquals(NAME, customerEntity.getName()),
                () -> assertEquals(SURNAME, customerEntity.getSurname()),
                () -> assertEquals(PHONE, customerEntity.getPhone()),
                () -> assertEquals(ADDRESS, customerEntity.getAddress())
        );

        verify(modelMapper, times(1)).map(customerRequest, CustomerEntity.class);
    }

}
