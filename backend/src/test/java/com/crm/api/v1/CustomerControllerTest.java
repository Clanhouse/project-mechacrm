package com.crm.api.v1;

import com.crm.dto.request.PageRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.service.impl.CustomerServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;

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

    @Mock
    private PageRequest pageRequest;

    @InjectMocks
    private CustomerController customerController;

    private static CustomerResponse customerResponse;

    @BeforeClass
    public static void setUp() {
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

    @Test
    public void shouldGetCustomersPaginated() {
        when(customerService.getCustomersPaginated(anyInt(), anyInt())).thenReturn(Page.empty());

        customerController.getCustomersPaginated(pageRequest);

        verify(customerService, times(1)).getCustomersPaginated(anyInt(), anyInt());
        verify(pageRequest, times(1)).getPage();
        verify(pageRequest, times(1)).getSize();
    }
}