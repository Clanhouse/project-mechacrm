package com.crm.api.v1;

import com.crm.dto.response.CustomerResponse;
import com.crm.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ApiOperation(value = "Finds all customers paginated", notes = "Add param \"page\" and/or \"size\""
            + " to specify page no. and size of each page.")
    public ResponseEntity<Page<CustomerResponse>> getCustomersPaginated(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(customerService.getCustomersPaginated(pageable));
    }
}
