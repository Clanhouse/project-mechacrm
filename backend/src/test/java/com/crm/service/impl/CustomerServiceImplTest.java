package com.crm.service.impl;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.response.CustomerResponse;
import com.crm.exception.CustomerNotFoundException;
import com.crm.exception.ErrorDict;
import com.crm.model.db.CustomerEntity;
import com.crm.repository.CustomerRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static io.jsonwebtoken.lang.Assert.notNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Jan";
    private static final String SURNAME = "Kowalski";
    private static final String PHONE = "665456987";
    private static final String ADDRESS = "Kaliska 12, Bełchatów";

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private static CustomerEntity customerEntity;

    @BeforeClass
    public static void setUp() {
        customerEntity = CustomerEntity.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .build();
    }

    @Test
    public void shouldGetCustomerById() {
        when(customerRepository.findById(ID)).thenReturn(Optional.of(customerEntity));
        when(mapper.convertToDto(customerEntity)).thenReturn(new CustomerResponse());

        final CustomerResponse response = customerService.getCustomerById(ID);

        verify(customerRepository, times(1)).findById(ID);
        notNull(response);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void shouldThrowCustomerNotFoundExceptionOnWrongCustomerId() {
        when(customerRepository.findById(ID)).thenThrow(new CustomerNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));

        customerService.getCustomerById(ID);

        verify(customerRepository, times(1)).findById(ID);
        verify(mapper, times(0)).convertToDto(any());
    }
}