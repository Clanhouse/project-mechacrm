package com.crm.service.impl;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.exception.CustomerException;
import com.crm.exception.CustomerNotFoundException;
import com.crm.exception.ErrorDict;
import com.crm.model.db.AddressEntity;
import com.crm.model.db.CustomerEntity;
import com.crm.repository.CustomerRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Jan";
    private static final String SURNAME = "Kowalski";
    private static final String PHONE = "665456987";

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private static AddressEntity addressEntity;
    private static CustomerEntity customerEntity;
    private static CustomerRequest customerRequest;
    private static CustomerResponse customerResponse;
    private static Pageable pageable;

    private static final int PAGE = 0;
    private static final int SIZE = 20;

    @BeforeClass
    public static void setUp() {
        addressEntity = AddressEntity.builder()
                .country("Poland")
                .city("Bełchatów")
                .postalCode("00-000")
                .streetName("Kaliska")
                .streetNumber("12")
                .build();

        customerEntity = CustomerEntity.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(addressEntity)
                .build();

        customerRequest = new CustomerRequest();
        customerResponse = new CustomerResponse();
        pageable = PageRequest.of(PAGE, SIZE);
    }

    @Test
    public void shouldGetCustomerById() {
        when(customerRepository.findById(ID)).thenReturn(Optional.of(customerEntity));
        when(customerMapper.convertToDto(customerEntity)).thenReturn(customerResponse);

        final CustomerResponse response = customerService.getCustomerById(ID);

        verify(customerRepository, times(1)).findById(ID);
        verify(customerMapper, times(1)).convertToDto(customerEntity);
        assertNotNull(response);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void shouldThrowCustomerNotFoundExceptionOnWrongCustomerId() {
        when(customerRepository.findById(ID)).thenThrow(new CustomerNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));

        customerService.getCustomerById(ID);

        verify(customerRepository, times(1)).findById(ID);
        verify(customerMapper, times(0)).convertToDto(any());
    }

    @Test
    public void shouldGetCustomersPaginated() {
        List<CustomerEntity> customerEntities = new ArrayList<>(Collections.singletonList(customerEntity));

        when(customerRepository.findAll(pageable)).thenReturn(new PageImpl<>(customerEntities, pageable, 1L));
        when(customerMapper.convertToDto(customerEntity)).thenReturn(customerResponse);

        final Page<CustomerResponse> response = customerService.getCustomersPaginated(PAGE, SIZE);

        verify(customerRepository, times(1)).findAll(pageable);
        verify(customerMapper, times(1)).convertToDto(customerEntity);
        assertNotNull(response);
    }

    @Test
    public void shouldAddCustomerWhenPhoneIsNotDuplicated() {
        customerRequest = CustomerRequest.builder()
                .phone(PHONE)
                .build();

        when(customerRepository.findByPhone(customerRequest.getPhone())).thenReturn(Optional.empty());
        when(customerMapper.convertToEntity(customerRequest)).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(customerMapper.convertToDto(customerEntity)).thenReturn(customerResponse);

        assertEquals(customerResponse, customerService.addCustomer(customerRequest));

        verify(customerRepository, times(1)).findByPhone(customerRequest.getPhone());
        verify(customerMapper, times(1)).convertToEntity(customerRequest);
        verify(customerRepository, times(1)).save(customerEntity);
        verify(customerMapper, times(1)).convertToDto(customerEntity);
    }

    @Test
    public void shouldThrowConflictWhenCustomerIsDuplicated() {
        customerRequest = CustomerRequest.builder()
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(addressEntity)
                .build();

        when(customerRepository.findByPhone(customerRequest.getPhone())).thenReturn(Optional.of(customerEntity));

        assertThatThrownBy(() -> customerService.addCustomer(customerRequest))
                .isInstanceOf(CustomerException.class)
                .hasMessage(ErrorDict.CUSTOMER_DUPLICATE);

        verify(customerRepository, times(1)).findByPhone(customerRequest.getPhone());
        verify(customerMapper, times(0)).convertToEntity(customerRequest);
        verify(customerRepository, times(0)).save(customerEntity);
        verify(customerMapper, times(0)).convertToDto(customerEntity);
    }

    @Test
    public void shouldThrowConflictWhenPhoneExists() {
        customerRequest = CustomerRequest.builder()
                .phone(PHONE)
                .build();

        when(customerRepository.findByPhone(customerRequest.getPhone())).thenReturn(Optional.of(customerEntity));

        assertThatThrownBy(() -> customerService.addCustomer(customerRequest))
                .isInstanceOf(CustomerException.class)
                .hasMessage(ErrorDict.CUSTOMER_CREATE_PHONE_EXISTS);

        verify(customerRepository, times(1)).findByPhone(customerRequest.getPhone());
        verify(customerMapper, times(0)).convertToEntity(customerRequest);
        verify(customerRepository, times(0)).save(customerEntity);
        verify(customerMapper, times(0)).convertToDto(customerEntity);
    }
}