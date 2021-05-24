package com.crm.api.v1;

import com.crm.dto.response.CarResponse;
import com.crm.service.CarServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarServiceImpl carService;

    @Autowired
    public CarController(final CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping
    @ApiOperation(value = "Finds all cars paginated", notes = "Add param \"page\" and/or \"size\""
            + " to specify page no. and size of each page.")
    public ResponseEntity<Page<CarResponse>> getCarsPaginated(@RequestParam(required = false, defaultValue = "0") final Integer page,
                                                              @RequestParam(required = false, defaultValue = "20") final Integer size) {
        return ResponseEntity.ok(carService.getCarsPaginated(page, size));
    }
}
