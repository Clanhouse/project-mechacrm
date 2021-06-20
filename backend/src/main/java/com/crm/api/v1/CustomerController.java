package com.crm.api.v1;

import com.crm.dto.request.CustomerRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ApiOperation(value = "Finds all customers paginated", notes = "Add param \"page\" and/or \"size\""
            + " to specify page no. and size of each page.")
    public ResponseEntity<Page<CustomerResponse>> getCustomersPaginated(@PageableDefault final Pageable pageable) {
        return ResponseEntity.ok(customerService.getCustomersPaginated(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@Valid @RequestBody final CustomerRequest customerRequest) {
        customerService.addCustomer(customerRequest);
    }
}
