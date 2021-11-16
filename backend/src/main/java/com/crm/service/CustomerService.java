package com.crm.service;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Page<CustomerResponse> getCustomersPaginated(final int page, final int size);

    CustomerResponse getCustomerById(final Long id);

    CustomerResponse addCustomer(final CustomerRequest customerRequest);
}
