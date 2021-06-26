package com.crm.api.v1;

import com.crm.dto.request.CustomerRequest;
import com.crm.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    private final String NAME = "John";
    private final String SURNAME = "Doe";
    private final String PHONE = "+48 948302394";
    private final String ADDRESS = "Test City";

    CustomerServiceImpl customerService = mock(CustomerServiceImpl.class);

    @InjectMocks
    private CustomerController customerController;

    private CustomerRequest customerRequest;


    @Before
    public void setUp() {
        customerRequest = CustomerRequest.builder()
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .build();
    }

    @Test
    public void createCustomer() {
        Mockito.doNothing().when(customerService).addCustomer(customerRequest);
        customerController.addCustomer(customerRequest);
        verify(customerService, times(1)).addCustomer(customerRequest);

    }
}
