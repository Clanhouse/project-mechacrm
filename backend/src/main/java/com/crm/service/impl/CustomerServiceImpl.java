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
        validateRequest(customerRequest);
        CustomerEntity newCustomer = customerMapper.convertToEntity(customerRequest);
        return customerMapper.convertToDto(customerRepository.save(newCustomer));
    }

    @Override
    public CustomerResponse updateCustomer(final CustomerRequest customerRequest, final Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND));

        validateRequest(customerRequest);
        customerEntity = customerMapper.updateProperties(customerEntity, customerRequest);
        CustomerEntity updatedEntity = customerRepository.save(customerEntity);

        return customerMapper.convertToDto(updatedEntity);
    }

    private void validateRequest(final CustomerRequest customerRequest) {
        Optional<CustomerEntity> customerByPhone = customerRepository.findByPhone(customerRequest.getPhone());
        customerByPhone.ifPresent(c -> {
            if (isDuplicateCustomer(customerRequest, c)) {
                throw new CustomerException(ErrorDict.CUSTOMER_DUPLICATE);
            } else {
                throw new CustomerException(ErrorDict.CUSTOMER_CREATE_PHONE_EXISTS);
            }
        });
    }

    @Override
    public void deleteCustomer(final Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(ErrorDict.CUSTOMER_NOT_FOUND);
        }

        customerRepository.deleteById(id);
    }

    private boolean isDuplicateCustomer(final CustomerRequest customerRequest, final CustomerEntity customerEntity) {
        return customerNameEquals(customerRequest, customerEntity)
                && customerSurnameEquals(customerRequest, customerEntity)
                && customerAddressEquals(customerRequest,customerEntity);
    }

    private boolean customerNameEquals(final CustomerRequest customerRequest, final CustomerEntity customerEntity) {
        return customerEntity.getName().equals(customerRequest.getName());
    }

    private boolean customerSurnameEquals(final CustomerRequest customerRequest, final CustomerEntity customerEntity) {
        return customerEntity.getSurname().equals(customerRequest.getSurname());
    }

    private boolean customerAddressEquals(final CustomerRequest customerRequest, final CustomerEntity customerEntity) {
        if (customerEntity.getAddress() != null && customerRequest.getAddress() != null) {
            return customerEntity.getAddress().equals(customerRequest.getAddress());
        } else return customerEntity.getAddress() == null && customerRequest.getAddress() == null;
    }
}
