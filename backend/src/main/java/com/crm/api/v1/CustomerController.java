package com.crm.api.v1;

import com.crm.dto.request.PageRequest;
import com.crm.dto.response.CustomerResponse;
import com.crm.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
