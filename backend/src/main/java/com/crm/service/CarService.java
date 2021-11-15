package com.crm.service;

import com.crm.dto.request.CarRequest;
import com.crm.dto.response.CarResponse;
import com.crm.model.db.CarEntity;
import org.springframework.data.domain.Page;

public interface CarService {

    Page<CarResponse> getCarsPaginated(final int page, final int size);

    CarResponse getCarById(final Long id);

    CarResponse getCarByRegistrationNumber(final String registrationNumber);

    CarResponse getCarByVIN(final String vin);

    CarResponse addCar(final CarRequest carRequest);
}
