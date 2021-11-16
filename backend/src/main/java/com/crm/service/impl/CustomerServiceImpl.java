package com.crm.service.impl;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.exception.CustomerException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Page<CustomerResponse> getCustomersPaginated(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerEntities = customerRepository.findAll(pageable);

        final List<CustomerResponse> customerResponses = customerEntities
                .getContent()
                .stream()
                .map(customerMapper::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(customerResponses, pageable, customerEntities.getTotalElements());
    }

    @Override
    public CustomerResponse getCustomerById(final Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::convertToDto)
                .orElseThrow(() -> new CustomerNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));
    }

    @Override
    public CustomerResponse addCustomer(final CustomerRequest customerRequest) {
        Optional<CustomerEntity> customerByPhone = customerRepository.findByPhone(customerRequest.getPhone());

        customerByPhone.ifPresent(c -> {
            if (isDuplicateCustomer(customerRequest, c)) {
                throw new CustomerException(ErrorDict.CUSTOMER_DUPLICATE);
            } else {
                throw new CustomerException(ErrorDict.CUSTOMER_CREATE_PHONE_EXISTS);
            }
        });

        CustomerEntity newCustomer = customerMapper.convertToEntity(customerRequest);
        CustomerEntity savedCustomer = customerRepository.save(newCustomer);

        return customerMapper.convertToDto(savedCustomer);
    }

    private boolean isDuplicateCustomer(CustomerRequest customerRequest, CustomerEntity c) {
        return c.getName().equals(customerRequest.getName()) && c.getSurname().equals(customerRequest.getSurname())
                && c.getAddress().equals(customerRequest.getAddress());
    }
}
