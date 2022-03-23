package com.crm.service.impl;

import com.crm.dto.mapper.CarMapper;
import com.crm.dto.request.CarRequest;
import com.crm.dto.response.CarResponse;
import com.crm.exception.CarException;
import com.crm.model.db.CarEntity;
import com.crm.model.db.CarTypeEntity;
import com.crm.repository.CarRepository;
import com.crm.repository.CarTypeRepository;
import com.crm.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.crm.exception.ErrorDict.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    @Value("${crm.properties.vin.length}")
    private int VIN_LENGTH;

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CarTypeRepository carTypeRepository;

    @Override
    public Page<CarResponse> getCarsPaginated(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CarEntity> carsPage = carRepository.findAll(pageable);

        final List<CarResponse> carResponseList = carsPage.getContent()
                .stream()
                .map(carMapper::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(carResponseList, pageable, carsPage.getTotalElements());
    }

    @Override
    public CarResponse getCarById(final Long id) {
        return carMapper.convertToDto(carRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(CAR_NOT_FOUND)));
    }

    @Override
    public CarResponse getCarByRegistrationNumber(final String registrationNumber) {
        return carRepository.findByRegistrationNumberIgnoreCase(registrationNumber)
                .map(carMapper::convertToDto)
                .orElseThrow(() -> new NoSuchElementException(REGISTRATION_NUMBER_NOT_FOUND));
    }

    @Override
    public CarResponse getCarByVIN(final String vin) {
        if (vin.length() != VIN_LENGTH)
            throw new NoSuchElementException(VIN_LENGTH_INVALID);

        if (hasVinIllegalCharacters(vin))
            throw new NoSuchElementException(VIN_FORMAT_INVALID);

        return carRepository.findByVinIgnoreCase(vin)
                .map(carMapper::convertToDto)
                .orElseThrow(() -> new NoSuchElementException(VIN_NOT_FOUND));
    }

    private boolean hasVinIllegalCharacters(final String vin) {
        return vin.toLowerCase().contains("o") || vin.toLowerCase().contains("i") || vin.toLowerCase().contains("q");
    }

    @Override
    public CarResponse addCar(CarRequest carRequest) {
        checkIfCarAlreadyExists(carRequest);
        String nameOfCarTypeEntity = carRequest.getCarTypeEntity().getName();

        CarTypeEntity carTypeEntity = carTypeRepository.findByNameIgnoreCase(nameOfCarTypeEntity)
                .orElseGet(this::determineDefaultCarType);

        CarEntity newCar = carMapper.convertToEntity(carRequest);
        newCar.setCarTypeEntity(carTypeEntity);

        return carMapper.convertToDto(carRepository.save(newCar));
    }

    private void checkIfCarAlreadyExists(CarRequest carRequest) {
        if (carRepository.findByRegistrationNumberIgnoreCase(carRequest.getRegistrationNumber()).isPresent()) {
            throw new CarException(CAR_CREATE_REGISTRATION_NUMBER_EXISTS);
        }

        if (carRepository.findByVinIgnoreCase(carRequest.getVin()).isPresent()) {
            throw new CarException(CAR_CREATE_VIN_EXISTS);
        }
    }

    private CarTypeEntity determineDefaultCarType() {
        return carTypeRepository.findByNameIgnoreCase("Other")
                .orElseGet(() -> CarTypeEntity.builder().name("Other").build());
    }
}
