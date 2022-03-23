package com.crm.api.v1;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.request.PageRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ApiOperation(value = "Finds all customers paginated", notes = "Add param \"page\" and/or \"size\""
            + " to specify page no. and size of each page.")
    public ResponseEntity<Page<CustomerResponse>> getCustomersPaginated(@Valid final PageRequest pageRequest) {
        return ResponseEntity.ok(customerService.getCustomersPaginated(pageRequest.getPage(), pageRequest.getSize()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds customer by id")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable final Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    @ApiOperation(value = "Adds new customer", notes = "Add body \"CustomerRequest\" to add new customer to database")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody final CustomerRequest customerRequest) {
        CustomerResponse savedCustomer = customerService.addCustomer(customerRequest);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedCustomer.getId().toString())
                        .build()
                        .toUri())
                .body(savedCustomer);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates existing customer", notes = "Add body \"CustomerRequest\" and path variable \"id\""
            + " of existing customer to proceed the update")
    public ResponseEntity<CustomerResponse> updateCustomer(@Valid @RequestBody final CustomerRequest customerRequest,
                                                           @PathVariable final Long id) {
        CustomerResponse updatedCustomer = customerService.updateCustomer(customerRequest, id);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes customer by id")
    public ResponseEntity<Void> deleteCustomer(@PathVariable final Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
