package com.crm.service.impl;

import com.crm.dto.mapper.CarMapper;
import com.crm.dto.request.CarRequest;
import com.crm.dto.response.CarResponse;
import com.crm.exception.CarHandlingException;
import com.crm.exception.CarNotFoundException;
import com.crm.exception.ErrorDict;
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
import java.util.stream.Collectors;

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

        return new PageImpl<>(carResponseList, pageable, carsPage.getTotalPages());
    }

    @Override
    public CarResponse getCarById(final Long id) {
        return carMapper.convertToDto(carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(ErrorDict.CAR_NOT_FOUND)));
    }

    @Override
    public CarResponse getCarByRegistrationNumber(final String registrationNumber) {
        return carRepository.findByRegistrationNumberIgnoreCase(registrationNumber)
                .map(carMapper::convertToDto)
                .orElseThrow(() -> new CarNotFoundException(ErrorDict.REGISTRATION_NUMBER_NOT_FOUND));
    }

    @Override
    public CarResponse getCarByVIN(final String vin) {
        if (vin.length() != VIN_LENGTH)
            throw new CarNotFoundException(ErrorDict.VIN_LENGTH_INVALID);

        if (hasVinIllegalCharacters(vin))
            throw new CarNotFoundException(ErrorDict.VIN_FORMAT_INVALID);

        return carRepository.findByVinIgnoreCase(vin)
                .map(carMapper::convertToDto)
                .orElseThrow(() -> new CarNotFoundException(ErrorDict.VIN_NOT_FOUND));
    }

    private boolean hasVinIllegalCharacters(final String vin) {
        return vin.toLowerCase().contains("o") || vin.toLowerCase().contains("i") || vin.toLowerCase().contains("q");
    }

    @Override
    public CarEntity addCar(CarRequest carRequest) {

        CarTypeEntity carTypeEntity;
        String nameOfCarTypeEntity = carRequest.getCarTypeEntity().getName();

        if (carRepository.findByRegistrationNumberIgnoreCase(carRequest.getRegistrationNumber()).isPresent()) {
            throw new CarHandlingException(ErrorDict.CAR_CREATE_REGISTRATION_NUMBER_EXISTS);
        }

        if (carRepository.findByVinIgnoreCase(carRequest.getVin()).isPresent()) {
            throw new CarHandlingException(ErrorDict.CAR_CREATE_VIN_EXISTS);
        }

        if (nameOfCarTypeEntity.isBlank()) {
            carTypeEntity = carTypeRepository.findByNameIgnoreCase("Other")
                    .orElseGet(() -> CarTypeEntity.builder().name("Other").build());
        } else {
            carTypeEntity = carTypeRepository.findByNameIgnoreCase(nameOfCarTypeEntity)
                    .orElseGet(() -> CarTypeEntity.builder().name(nameOfCarTypeEntity).build());
        }

        CarEntity newCar = carMapper.convertToEntity(carRequest);
        newCar.setCarTypeEntity(carTypeEntity);

        return carRepository.save(newCar);
    }
}
