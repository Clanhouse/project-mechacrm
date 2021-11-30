package com.crm.dto.mapper;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.model.db.AddressEntity;
import com.crm.model.db.CustomerEntity;
import org.junit.BeforeClass;
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

    private static CustomerRequest customerRequest;
    private static CustomerEntity customerEntity;
    private static AddressEntity addressEntity;

    private static final Long ID = 1L;
    private static final String NAME = "test";
    private static final String SURNAME = "test";
    private static final String PHONE = "+48 111111111";

    @BeforeClass
    public static void setUp() {
        addressEntity = AddressEntity.builder()
                .country("Poland")
                .city("Bełchatów")
                .postalCode("00-000")
                .streetName("Kaliska")
                .streetNumber("12")
                .build();

        customerRequest = CustomerRequest.builder()
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(addressEntity)
                .build();

        customerEntity = CustomerEntity.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(addressEntity)
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
                () -> assertEquals(addressEntity, customerResponse.getAddress())
        );

        verify(modelMapper, times(1)).map(customerEntity, CustomerResponse.class);
    }

    @Test
    public void shouldReturnMappedCustomerRequestToCustomerEntity() {
        final CustomerEntity customerEntity = customerMapper.convertToEntity(customerRequest);

        assertAll(
                () -> assertEquals(NAME, customerEntity.getName()),
                () -> assertEquals(SURNAME, customerEntity.getSurname()),
                () -> assertEquals(PHONE, customerEntity.getPhone()),
                () -> assertEquals(addressEntity, customerEntity.getAddress())
        );

        verify(modelMapper, times(1)).map(customerRequest, CustomerEntity.class);
    }

}
