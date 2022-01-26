package com.crm.api.v1;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.request.PageRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.model.db.AddressEntity;
import com.crm.service.impl.CustomerServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    private static final Long ID = 1L;
    private static final String NAME = "Jan";
    private static final String SURNAME = "Kowalski";
    private static final String PHONE = "665456987";

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private PageRequest pageRequest;

    @InjectMocks
    private CustomerController customerController;

    private static CustomerResponse customerResponse;
    private static CustomerRequest customerRequest;

    @BeforeClass
    public static void setUp() {
        HttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        AddressEntity addressEntity = AddressEntity.builder()
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

        customerResponse = CustomerResponse.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(addressEntity)
                .build();
    }

    @Test
    public void shouldGetCustomerById() {
        when(customerService.getCustomerById(ID)).thenReturn(customerResponse);

        customerController.getCustomerById(ID);

        verify(customerService, times(1)).getCustomerById(ID);
    }

    @Test
    public void shouldGetCustomersPaginated() {
        when(customerService.getCustomersPaginated(anyInt(), anyInt())).thenReturn(Page.empty());

        customerController.getCustomersPaginated(pageRequest);

        verify(customerService, times(1)).getCustomersPaginated(anyInt(), anyInt());
        verify(pageRequest, times(1)).getPage();
        verify(pageRequest, times(1)).getSize();
    }

    @Test
    public void shouldAddCustomer() {
        when(customerService.addCustomer(customerRequest)).thenReturn(customerResponse);

        customerController.addCustomer(customerRequest);

        verify(customerService, times(1)).addCustomer(customerRequest);
    }

    @Test
    public void shouldDeleteCustomer() {
        customerController.deleteCustomer(ID);

        verify(customerService, times(1)).deleteCustomer(ID);
    }
}