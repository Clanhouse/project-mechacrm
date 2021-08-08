package com.crm.service;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<CustomerResponse> getCustomersPaginated(final int page, final int size);

    CustomerResponse getCustomerById(Long id);

    void addCustomer(CustomerRequest customerRequest);
}
