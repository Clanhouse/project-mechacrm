package com.crm.service.impl;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.exception.CustomerException;
import com.crm.exception.ErrorDict;
import com.crm.model.db.CustomerEntity;
import com.crm.repository.CustomerRepository;
import com.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public Page<CustomerResponse> getCustomersPaginated(final Pageable pageable) {
        Page<CustomerEntity> customerEntities = repository.findAll(pageable);

        final List<CustomerResponse> customerResponses = customerEntities
                .getContent()
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(customerResponses, pageable, customerEntities.getTotalElements());
    }

    @Override
    public void addCustomer(CustomerRequest customerRequest) {
        try {
            repository.save(mapper.convertToEntity(customerRequest));
        } catch (RuntimeException exc) {
            throw new CustomerException(ErrorDict.CUSTOMER_INSERT_IMPOSSIBLE);
        }
    }

}
