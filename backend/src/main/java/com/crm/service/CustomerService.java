package com.crm.service;

import com.crm.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

    Page<CustomerResponse> getCustomersPaginated(Pageable pageable);

    CustomerResponse getCustomerById(Long id);
}
