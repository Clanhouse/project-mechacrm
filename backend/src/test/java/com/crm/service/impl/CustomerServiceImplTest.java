package com.crm.service.impl;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.exception.CustomerException;
import com.crm.exception.ErrorDict;
import com.crm.exception.ResourceNotFoundException;
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
import static org.junit.Assert.assertNotEquals;
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

    private static final int PAGE = 0;
    private static final int SIZE = 20;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private static CustomerEntity customerEntity;

    private static final CustomerRequest customerRequest = new CustomerRequest();
    private static final CustomerResponse customerResponse = new CustomerResponse();
    private static final Pageable pageable = PageRequest.of(PAGE, SIZE);

    @BeforeClass
    public static void setUp() {
        customerEntity = createCustomerEntity();
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

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowCustomerNotFoundWhenGetCustomerWithWrongId() {
        when(customerRepository.findById(ID)).thenThrow(new ResourceNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));

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
        var customerRequest = createCustomerRequest();
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
    public void shouldNotAddCustomerAndThrowConflictWhenCustomerIsDuplicated() {
        var customerRequest = createCustomerRequest();
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
    public void shouldNotAddCustomerAndThrowConflictWhenPhoneExists() {
        var customerRequest = CustomerRequest.builder()
                .phone(PHONE)
                .build();

        when(customerRepository.findByPhone(customerRequest.getPhone())).thenReturn(Optional.of(customerEntity));

        assertThatThrownBy(() -> customerService.addCustomer(customerRequest))
                .isInstanceOf(CustomerException.class)
                .hasMessage(ErrorDict.CUSTOMER_PHONE_EXISTS);

        verify(customerRepository, times(1)).findByPhone(customerRequest.getPhone());
        verify(customerMapper, times(0)).convertToEntity(customerRequest);
        verify(customerRepository, times(0)).save(customerEntity);
        verify(customerMapper, times(0)).convertToDto(customerEntity);
    }

    @Test
    public void shouldUpdateCustomerWhenPhoneIsNotDuplicated () {
        var customerRequest = createCustomerRequest();
        var updatedCustomer = CustomerEntity.builder()
                .name("Jan")
                .surname("Kowalski")
                .phone("661662663")
                .address(createAddressEntity())
                .build();

        when(customerRepository.findById(ID)).thenReturn(Optional.ofNullable(customerEntity));
        when(customerRepository.findByPhone(customerRequest.getPhone())).thenReturn(Optional.empty());
        when(customerMapper.updateProperties(customerEntity, customerRequest)).thenReturn(updatedCustomer);
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);
        when(customerMapper.convertToDto(updatedCustomer)).thenReturn(customerResponse);

        assertEquals(customerResponse, customerService.updateCustomer(customerRequest, ID));
        assertNotEquals(customerEntity, updatedCustomer);

        verify(customerRepository, times(1)).findById(ID);
        verify(customerRepository, times(1)).findByPhone(customerRequest.getPhone());
        verify(customerMapper, times(1)).updateProperties(customerEntity, customerRequest);
        verify(customerRepository, times(1)).save(updatedCustomer);
        verify(customerMapper, times(1)).convertToDto(updatedCustomer);
    }

    @Test
    public void shouldThrowCustomerNotFoundWhenUpdateCustomerWithWrongId () {
        when(customerRepository.findById(ID)).thenThrow(new ResourceNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));

        assertThatThrownBy(() -> customerService.updateCustomer(customerRequest, ID))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(ErrorDict.CUSTOMER_NOT_FOUND);

        verify(customerRepository, times(1)).findById(ID);
        verify(customerRepository, times(0)).findByPhone(customerRequest.getPhone());
        verify(customerMapper, times(0)).updateProperties(customerEntity, customerRequest);
        verify(customerRepository, times(0)).save(any());
        verify(customerMapper, times(0)).convertToDto(any());
    }

    @Test
    public void shouldNotUpdateCustomerAndThrowConflictWhenCustomerIsDuplicated () {
        var customerRequest = createCustomerRequest();
        when(customerRepository.findById(ID)).thenReturn(Optional.ofNullable(customerEntity));
        when(customerRepository.findByPhone(customerRequest.getPhone())).thenReturn(Optional.of(customerEntity));

        assertThatThrownBy(() -> customerService.updateCustomer(customerRequest, ID))
                .isInstanceOf(CustomerException.class)
                .hasMessage(ErrorDict.CUSTOMER_DUPLICATE);

        verify(customerRepository, times(1)).findById(ID);
        verify(customerRepository, times(1)).findByPhone(customerRequest.getPhone());
        verify(customerMapper, times(0)).updateProperties(customerEntity, customerRequest);
        verify(customerRepository, times(0)).save(any());
        verify(customerMapper, times(0)).convertToDto(any());
    }

    @Test
    public void shouldDeleteCustomerById() {
        when(customerRepository.existsById(ID)).thenReturn(true);

        customerService.deleteCustomer(ID);

        verify(customerRepository, times(1)).existsById(ID);
        verify(customerRepository, times(1)).deleteById(ID);
    }

    @Test
    public void shouldThrowCustomerNotFoundWhenDeleteCustomerWithWrongId() {
        when(customerRepository.existsById(ID)).thenReturn(false);

        assertThatThrownBy(() -> customerService.deleteCustomer(ID))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(ErrorDict.CUSTOMER_NOT_FOUND);

        verify(customerRepository, times(1)).existsById(ID);
        verify(customerRepository, times(0)).deleteById(ID);

    }

    private static CustomerEntity createCustomerEntity () {
        return CustomerEntity.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(createAddressEntity())
                .build();
    }

    private CustomerRequest createCustomerRequest () {
        return CustomerRequest.builder()
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(createAddressEntity())
                .build();
    }

    private static AddressEntity createAddressEntity () {
        return AddressEntity.builder()
                .country("Poland")
                .city("Bełchatów")
                .postalCode("00-000")
                .streetName("Kaliska")
                .streetNumber("12")
                .build();
    }
}