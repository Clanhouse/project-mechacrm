package com.crm.api.v1;

import com.crm.dto.response.CarResponse;
import com.crm.service.CarServiceImpl;
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
    public ResponseEntity<Page<CarResponse>> getCarsPaginated(@RequestParam(required = false) final int page,
                                        @RequestParam(required = false, defaultValue = "20") final int size) {
        return ResponseEntity.ok(carService.getCarsPaginated(page, size));
    }
}
