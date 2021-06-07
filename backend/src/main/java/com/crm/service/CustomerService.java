package com.crm.service;

import com.crm.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<CustomerResponse> getCustomersPaginated(Pageable pageable);
}
