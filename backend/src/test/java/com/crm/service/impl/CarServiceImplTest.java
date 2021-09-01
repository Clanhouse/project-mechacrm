package com.crm.service.impl;

import com.crm.dto.mapper.CarMapper;
import com.crm.dto.response.CarResponse;
import com.crm.exception.CarNotFoundException;
import com.crm.model.db.CarEntity;
import com.crm.repository.CarRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    private static final Long ONE = 1L;
    private static final String REGISTRATION_NUMBER = "ABC123";

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    private static CarEntity carEntity;
    private static CarResponse carResponse;

    @BeforeClass
    public static void setUp() {
        carEntity = new CarEntity();
        carResponse = new CarResponse();
    }

    @Test
    public void getCarByIdShouldReturnCorrectResponse() {
        final Optional<CarEntity> optionalCarEntity = Optional.of(CarServiceImplTest.carEntity);
        when(carRepository.findById(ONE)).thenReturn(optionalCarEntity);
        when(carMapper.convertToDto(carEntity)).thenReturn(carResponse);

        carService.getCarById(ONE);

        verify(carRepository, times(1)).findById(ONE);
        verify(carMapper, times(1)).convertToDto(carEntity);
    }

    @Test(expected = CarNotFoundException.class)
    public void getCarByIdShouldThrowExceptionForNotExistingEntry() {
        when(carRepository.findById(ONE)).thenReturn(Optional.empty());

        carService.getCarById(ONE);

        verify(carRepository, times(1)).findById(ONE);
        verify(carMapper, times(0)).convertToDto(any());
    }

    @Test
    public void getCarByRegistrationNumberShouldReturnCorrectResponse() {
        when(carRepository.findByRegistrationNumberIgnoreCase(REGISTRATION_NUMBER)).thenReturn(Optional.of(carEntity));
        when(carMapper.convertToDto(carEntity)).thenReturn(carResponse);

        final CarResponse response = carService.getCarByRegistrationNumber(REGISTRATION_NUMBER);

        verify(carRepository, times(1)).findByRegistrationNumberIgnoreCase(REGISTRATION_NUMBER);
        verify(carMapper, times(1)).convertToDto(any());
        assertNotNull(response);
    }

    @Test(expected = CarNotFoundException.class)
    public void getCarByRegistrationNumberShouldThrowExceptionForInvalidRegistrationNumber() {
        when(carRepository.findByRegistrationNumberIgnoreCase(REGISTRATION_NUMBER)).thenReturn(Optional.empty());

        carService.getCarByRegistrationNumber(REGISTRATION_NUMBER);

        verify(carRepository, times(1)).findByRegistrationNumberIgnoreCase(REGISTRATION_NUMBER);
        verify(carMapper, times(0)).convertToDto(any());
    }
}
