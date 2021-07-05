package com.crm.service.impl;

import com.crm.dto.mapper.CarMapper;
import com.crm.dto.response.CarResponse;
import com.crm.exception.CarNotFoundException;
import com.crm.exception.ErrorDict;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void shouldThrowCarNotFoundForVinLengthLessThenSeventeenCharacters() {
        //given
        String VIN_TOO_SHORT = "V455";

        //then
        assertThatThrownBy(() -> carService.getCarByVIN(VIN_TOO_SHORT))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_INVALID_LENGTH);
    }

    @Test
    public void shouldThrowCarNotFoundForVinLengthMoreThenSeventeenCharacters() {
        //given
        String VIN_TOO_LONG = "V455467475455845FDLKPRTFGG";

        //then
        assertThatThrownBy(() -> carService.getCarByVIN(VIN_TOO_LONG))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_INVALID_LENGTH);
    }

    @Test
    public void shouldThrowCarNotFoundForVinContainCharacter_O() {
        //given
        String VIN_WITH_CHAR_O = "VIN4003876543920O";

        //when
        assertThatThrownBy(() -> carService.getCarByVIN(VIN_WITH_CHAR_O))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_ILLEGAL_CHARACTERS);
    }

    @Test
    public void shouldThrowCarNotFoundForVinContainCharacter_I() {
        //given
        String VIN_WITH_CHAR_O = "VIN4003876543920i";

        //when
        assertThatThrownBy(() -> carService.getCarByVIN(VIN_WITH_CHAR_O))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_ILLEGAL_CHARACTERS);
    }

    @Test
    public void shouldThrowCarNotFoundForVinContainCharacter_Q() {
        //given
        String VIN_WITH_CHAR_O = "VINq4003876543920";

        //when
        assertThatThrownBy(() -> carService.getCarByVIN(VIN_WITH_CHAR_O))
                .isInstanceOf(CarNotFoundException.class)
                .hasMessage(ErrorDict.VIN_ILLEGAL_CHARACTERS);
    }


    @Test
    public void shouldGetCarByVin() {
        //given
        String vin = "VWVWVW12345678901";

        //when
        when(carRepository.findCarEntityByVinIgnoreCase(vin)).thenReturn(Optional.of(carEntity));
        when(carMapper.convertToDto(carEntity)).thenReturn(carResponse);
        when(carService.getCarByVIN(vin)).thenReturn(carResponse);

        //then
        assertEquals(carResponse, carService.getCarByVIN(vin));
    }
}
