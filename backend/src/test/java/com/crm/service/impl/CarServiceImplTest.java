package com.crm.service.impl;

import com.crm.dto.mapper.CarMapper;
import com.crm.dto.request.CarRequest;
import com.crm.dto.response.CarResponse;
import com.crm.exception.CarException;
import com.crm.exception.CarNotFoundException;
import com.crm.exception.ErrorDict;
import com.crm.model.db.CarEntity;
import com.crm.model.db.CarTypeEntity;
import com.crm.repository.CarRepository;
import com.crm.repository.CarTypeRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    private static final Long ONE = 1L;
    private static final String REGISTRATION_NUMBER = "ABC123";
    private static final String VIN = "VWVWVW12345678901";

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarTypeRepository carTypeRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    private static CarEntity carEntity;
    private static CarResponse carResponse;
    private static CarRequest carRequest;

    @BeforeClass
    public static void setUp() {
        carEntity = new CarEntity();
        carResponse = new CarResponse();
        carRequest = new CarRequest();
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

        assertEquals(carResponse, carService.getCarByRegistrationNumber(REGISTRATION_NUMBER));

        verify(carRepository, times(1)).findByRegistrationNumberIgnoreCase(REGISTRATION_NUMBER);
        verify(carMapper, times(1)).convertToDto(any());
    }

    @Test(expected = CarNotFoundException.class)
    public void getCarByRegistrationNumberShouldThrowExceptionForInvalidRegistrationNumber() {
        when(carRepository.findByRegistrationNumberIgnoreCase(REGISTRATION_NUMBER)).thenReturn(Optional.empty());

        carService.getCarByRegistrationNumber(REGISTRATION_NUMBER);

        verify(carRepository, times(1)).findByRegistrationNumberIgnoreCase(REGISTRATION_NUMBER);
        verify(carMapper, times(0)).convertToDto(any());
    }

    @Test
    public void shouldThrowCarNotFoundForVinLengthLessThenSeventeenCharacters() {
        final String vinTooShort = "V455";

        assertThatThrownBy(() -> carService.getCarByVIN(vinTooShort))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_LENGTH_INVALID);

        verify(carRepository, times(0)).findByVinIgnoreCase(vinTooShort);
    }

    @Test
    public void shouldThrowCarNotFoundForVinLengthMoreThenSeventeenCharacters() {
        final String vinTooLong = "V455467475455845FDLKPRTFGG";

        assertThatThrownBy(() -> carService.getCarByVIN(vinTooLong))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_LENGTH_INVALID);

        verify(carRepository, times(0)).findByVinIgnoreCase(vinTooLong);
    }

    @Test
    public void shouldThrowCarNotFoundForVinContainCharacter_O() {
        ReflectionTestUtils.setField(carService, "VIN_LENGTH", 17);
        final String vinWithCharO = "VIN4003876543920O";

        assertThatThrownBy(() -> carService.getCarByVIN(vinWithCharO))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_FORMAT_INVALID);

        verify(carRepository, times(0)).findByVinIgnoreCase(vinWithCharO);
    }

    @Test
    public void shouldThrowCarNotFoundForVinContainCharacter_I() {
        ReflectionTestUtils.setField(carService, "VIN_LENGTH", 17);
        final String vinWithCharI = "VIN4003876543920i";

        assertThatThrownBy(() -> carService.getCarByVIN(vinWithCharI))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_FORMAT_INVALID);

        verify(carRepository, times(0)).findByVinIgnoreCase(vinWithCharI);
    }

    @Test
    public void shouldThrowCarNotFoundForVinContainCharacter_Q() {
        ReflectionTestUtils.setField(carService, "VIN_LENGTH", 17);
        final String vinWithCharQ = "VINq4003876543920";

        assertThatThrownBy(() -> carService.getCarByVIN(vinWithCharQ))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_FORMAT_INVALID);

        verify(carRepository, times(0)).findByVinIgnoreCase(vinWithCharQ);
    }


    @Test
    public void shouldGetCarByVin() {
        ReflectionTestUtils.setField(carService, "VIN_LENGTH", 17);

        when(carRepository.findByVinIgnoreCase(VIN)).thenReturn(Optional.of(carEntity));
        when(carMapper.convertToDto(carEntity)).thenReturn(carResponse);

        assertEquals(carResponse, carService.getCarByVIN(VIN));

        verify(carRepository, times(1)).findByVinIgnoreCase(VIN);
        verify(carMapper, times(1)).convertToDto(carEntity);
    }

    @Test
    public void shouldThrowConflictWhenVinExists() {
        final String existingVin = "VWVWVW12345678901";
        carRequest = CarRequest.builder()
                .vin(existingVin)
                .carTypeEntity(CarTypeEntity.builder().name("").build())
                .build();

        carEntity = CarEntity.builder().vin(existingVin).build();

        when(carRepository.findByVinIgnoreCase(existingVin)).thenReturn(Optional.of(carEntity));

        assertThatThrownBy(() -> carService.addCar(carRequest))
                .isInstanceOf(CarException.class)
                .hasMessage(ErrorDict.CAR_CREATE_VIN_EXISTS);

        verify(carRepository, times(1)).findByVinIgnoreCase(existingVin);
    }

    @Test
    public void shouldThrowConflictWhenRegistrationNumberExists() {
        final String existingRegistrationNumber = "KR12345";
        carRequest = CarRequest.builder()
                .registrationNumber(existingRegistrationNumber)
                .carTypeEntity(CarTypeEntity.builder().name("").build())
                .build();

        carEntity = CarEntity.builder().registrationNumber(existingRegistrationNumber).build();

        when(carRepository.findByRegistrationNumberIgnoreCase(existingRegistrationNumber))
                .thenReturn(Optional.of(carEntity));

        assertThatThrownBy(() -> carService.addCar(carRequest))
                .isInstanceOf(CarException.class)
                .hasMessage(ErrorDict.CAR_CREATE_REGISTRATION_NUMBER_EXISTS);

        verify(carRepository, times(1)).findByRegistrationNumberIgnoreCase(existingRegistrationNumber);
    }

    @Test
    public void shouldAddCarWithTypeOtherWhenCarTypeNotProvided() {
        carRequest = CarRequest.builder()
                .carTypeEntity(CarTypeEntity.builder().name("").build())
                .build();

        carResponse = CarResponse.builder()
                .carTypeEntity(CarTypeEntity.builder().name("Other").build())
                .build();

        when(carTypeRepository.findByNameIgnoreCase("Other"))
                .thenReturn(Optional.of(CarTypeEntity.builder().name("Other").build()));
        when(carMapper.convertToEntity(carRequest)).thenReturn(carEntity);
        when(carRepository.save(carEntity)).thenReturn(carEntity);
        when(carMapper.convertToDto(carEntity)).thenReturn(carResponse);

        CarResponse addedCar = carService.addCar(carRequest);

        assertEquals("Other", addedCar.getCarTypeEntity().getName());

        verify(carTypeRepository, times(1)).findByNameIgnoreCase("Other");
        verify(carMapper, times(1)).convertToEntity(carRequest);
        verify(carRepository, times(1)).save(carEntity);
        verify(carMapper, times(1)).convertToDto(carEntity);
    }

    @Test
    public void shouldAddCarWhenCarTypeProvided() {
        final String carTypeName = "Sedan";

        carRequest = CarRequest.builder()
                .carTypeEntity(CarTypeEntity.builder().name(carTypeName).build())
                .build();

        carResponse = CarResponse.builder()
                .carTypeEntity(CarTypeEntity.builder().name(carTypeName).build())
                .build();

        when(carTypeRepository.findByNameIgnoreCase(carTypeName))
                .thenReturn(Optional.of(CarTypeEntity.builder().name(carTypeName).build()));
        when(carMapper.convertToEntity(carRequest)).thenReturn(carEntity);
        when(carRepository.save(carEntity)).thenReturn(carEntity);
        when(carMapper.convertToDto(carEntity)).thenReturn(carResponse);

        CarResponse addedCar = carService.addCar(carRequest);

        assertEquals(carTypeName, addedCar.getCarTypeEntity().getName());

        verify(carTypeRepository, times(1)).findByNameIgnoreCase(carTypeName);
        verify(carMapper, times(1)).convertToEntity(carRequest);
        verify(carRepository, times(1)).save(carEntity);
        verify(carMapper, times(1)).convertToDto(carEntity);
    }
}
