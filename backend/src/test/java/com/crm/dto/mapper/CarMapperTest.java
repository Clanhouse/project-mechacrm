package com.crm.dto.mapper;

import com.crm.dto.request.CarRequest;
import com.crm.dto.response.CarResponse;
import com.crm.model.db.CarEntity;
import com.crm.model.db.CarTypeEntity;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CarMapperTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private CarMapper carMapper;

    private static CarRequest carRequest;
    private static CarEntity carEntity;
    private static CarTypeEntity carTypeEntity;

    private static final Long ID = 1L;
    private static final String VIN = "test";
    private static final String REGISTRATION_NUMBER = "test";
    private static final String BRAND = "test";
    private static final String MODEL = "test";
    private static final Integer PRODUCTION_YEAR = 2010;
    private static final Integer MILEAGE = 123456;
    private static final String DESCRIPTION = "test";

    @BeforeClass
    public static void setUp() {
        carTypeEntity = CarTypeEntity.builder()
                .id(ID)
                .name("type")
                .build();

        carRequest = CarRequest.builder()
                .id(ID)
                .vin(VIN)
                .registrationNumber(REGISTRATION_NUMBER)
                .brand(BRAND)
                .model(MODEL)
                .productionYear(PRODUCTION_YEAR)
                .mileage(MILEAGE)
                .description(DESCRIPTION)
                .carTypeEntity(carTypeEntity)
                .build();

        carEntity = CarEntity.builder()
                .id(ID)
                .vin(VIN)
                .registrationNumber(REGISTRATION_NUMBER)
                .brand(BRAND)
                .model(MODEL)
                .productionYear(PRODUCTION_YEAR)
                .mileage(MILEAGE)
                .description(DESCRIPTION)
                .carTypeEntity(carTypeEntity)
                .build();
    }

    @Test
    public void shouldReturnMappedCarEntityToCarResponse() {
        final CarResponse carResponse = carMapper.convertToDto(carEntity);

        assertAll(
                () -> assertEquals(ID, carResponse.getId()),
                () -> assertEquals(VIN, carResponse.getVin()),
                () -> assertEquals(REGISTRATION_NUMBER, carResponse.getRegistrationNumber()),
                () -> assertEquals(BRAND, carResponse.getBrand()),
                () -> assertEquals(MODEL, carResponse.getModel()),
                () -> assertEquals(PRODUCTION_YEAR, carResponse.getProductionYear()),
                () -> assertEquals(MILEAGE, carResponse.getMileage()),
                () -> assertEquals(DESCRIPTION, carResponse.getDescription()),
                () -> assertEquals(carTypeEntity, carResponse.getCarTypeEntity())
        );

        verify(modelMapper, times(1)).map(carEntity, CarResponse.class);
    }

    @Test
    public void shouldReturnMappedCarRequestToCarEntity() {
        final CarEntity carEntity = carMapper.convertToEntity(carRequest);

        assertAll(
                () -> assertEquals(ID, carEntity.getId()),
                () -> assertEquals(VIN, carEntity.getVin()),
                () -> assertEquals(REGISTRATION_NUMBER, carEntity.getRegistrationNumber()),
                () -> assertEquals(BRAND, carEntity.getBrand()),
                () -> assertEquals(MODEL, carEntity.getModel()),
                () -> assertEquals(PRODUCTION_YEAR, carEntity.getProductionYear()),
                () -> assertEquals(MILEAGE, carEntity.getMileage()),
                () -> assertEquals(DESCRIPTION, carEntity.getDescription()),
                () -> assertEquals(carTypeEntity, carEntity.getCarTypeEntity())
        );

        verify(modelMapper, times(1)).map(carRequest, CarEntity.class);
    }

}