package com.crm.api.v1;

import com.crm.dto.response.CustomerResponse;
import com.crm.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    private static final Long ID = 1L;
    private static final String NAME = "Jan";
    private static final String SURNAME = "Kowalski";
    private static final String PHONE = "665456987";
    private static final String ADDRESS = "Kaliska 12, Bełchatów";

    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    private CustomerResponse customerResponse;

    @Before
    public void setUp() {
        customerResponse = CustomerResponse.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .build();
    }

    @Test
    public void shouldGetCustomerById() {
        when(customerService.getCustomerById(ID)).thenReturn(customerResponse);
        customerController.getCustomerById(ID);
        verify(customerService, times(1)).getCustomerById(ID);
    }
}