package com.crm.api.v1;

import com.crm.dto.response.CarResponse;
import com.crm.service.impl.CarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {

    private final Long ID = 1L;
    private final String VIN = "JT3Z123KBW1589043";
    private final String REGISTRATION_NUMBER = "KR12PR";
    private final String BRAND = "Toyota";
    private final String MODEL = "Avensis";
    private final int PRODUCTION_YEAR = 2014;
    private final int MILEAGE = 123;
    private final String DESCRIPTION = "Test description";

    CarServiceImpl carService = mock(CarServiceImpl.class);

    @InjectMocks
    private CarController carController;

    private CarResponse carResponse;

    @Before
    public void setUp() {
        carResponse = CarResponse.builder()
                .vin(VIN)
                .registrationNumber(REGISTRATION_NUMBER)
                .brand(BRAND)
                .model(MODEL)
                .productionYear(PRODUCTION_YEAR)
                .mileage(MILEAGE)
                .description(DESCRIPTION)
                .build();
    }

    @Test
    public void getCarById() {
        Mockito.when(carService.getCarById(ID)).thenReturn(carResponse);
        carController.getCarById(ID);
        verify(carService, times(1)).getCarById(ID);
    }
}
