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
        addressEntity = createAddressEntity();
        customerRequest = createCustomerRequest();
        customerEntity = createCustomerEntity();
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

    @Test
    public void shouldUpdatePropertiesOfCustomerEntity() {
        var existingCustomer = createCustomerEntity();
        var updateRequest = CustomerRequest.builder()
                .name("test1")
                .surname("test1")
                .phone("500500500")
                .address(addressEntity)
                .build();

        final CustomerEntity updatedCustomer = customerMapper.updateProperties(existingCustomer, updateRequest);

        assertAll(
                () -> assertEquals(updateRequest.getName(), updatedCustomer.getName()),
                () -> assertEquals(updateRequest.getSurname(), updatedCustomer.getSurname()),
                () -> assertEquals(updateRequest.getPhone(), updatedCustomer.getPhone()),
                () -> assertEquals(updateRequest.getAddress(), updatedCustomer.getAddress())
        );
    }

    private static CustomerEntity createCustomerEntity() {
        return CustomerEntity.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(addressEntity)
                .cars(new HashSet<>())
                .build();
    }

    private static CustomerRequest createCustomerRequest() {
        return CustomerRequest.builder()
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(addressEntity)
                .build();
    }

    private static AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .country("Poland")
                .city("Bełchatów")
                .postalCode("00-000")
                .streetName("Kaliska")
                .streetNumber("12")
                .build();
    }
}
