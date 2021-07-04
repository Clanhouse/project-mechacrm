package com.crm.service.impl;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.exception.CustomerNotFoundException;
import com.crm.exception.ErrorDict;
import com.crm.model.db.CustomerEntity;
import com.crm.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
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

    private CustomerEntity customerEntity;

    @Before
    public void setUp() {
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
        customerRepository.findById(ID).map(mapper::convertToDto);
        verify(customerRepository, times(1)).findById(ID);
    }

    @Test
    public void shouldThrowCustomerNotFoundException() {
        when(customerRepository.findById(ID)).thenThrow(new CustomerNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));

        Assertions.assertThatThrownBy(() -> customerService.getCustomerById(ID))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("Customer with provided id doesn't exist");
    }
}