package com.crm.api.v1;

import com.crm.dto.response.CarResponse;
import com.crm.service.CarServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarServiceImpl carService;

    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public Page<CarResponse> getCarsPaginated(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "20") int size) {
        return carService.getCarsPaginated(page, size);
    }
}
