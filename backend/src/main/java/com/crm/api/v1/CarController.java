package com.crm.api.v1;

import com.crm.dto.request.PageRequest;
import com.crm.dto.response.CarResponse;
import com.crm.service.impl.CarServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarServiceImpl carService;

    @GetMapping
    @ApiOperation(value = "Finds all cars paginated", notes = "Add param \"page\" and/or \"size\""
            + " to specify page no. and size of each page.")
    public ResponseEntity<Page<CarResponse>> getCarsPaginated(@Valid PageRequest pageRequest) {
        return ResponseEntity.ok(carService.getCarsPaginated(pageRequest.getPage(), pageRequest.getSize()));
    }
}
