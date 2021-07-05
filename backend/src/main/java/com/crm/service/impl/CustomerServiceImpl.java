package com.crm.service.impl;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.response.CustomerResponse;
import com.crm.exception.CustomerNotFoundException;
import com.crm.exception.ErrorDict;
import com.crm.model.db.CustomerEntity;
import com.crm.repository.CustomerRepository;
import com.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public Page<CustomerResponse> getCustomersPaginated(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerEntities = repository.findAll(pageable);

        final List<CustomerResponse> customerResponses = customerEntities
                .getContent()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(customerResponses, pageable, customerEntities.getTotalElements());
    }

    @Override
    public CustomerResponse getCustomerById(final Long id) {
        return repository.findById(id)
                .map(mapper::convertToDto)
                .orElseThrow(() -> new CustomerNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));
    }
}
