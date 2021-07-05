package com.crm.api.v1;

import com.crm.dto.response.CarResponse;
import com.crm.service.impl.CarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {

    private final static Long ID = 1L;
    private final static String VIN = "JT3Z123KBW1589043";
    private final static String REGISTRATION_NUMBER = "KR12PR";
    private final static String BRAND = "Toyota";
    private final static String MODEL = "Avensis";
    private final static int PRODUCTION_YEAR = 2014;
    private final static int MILEAGE = 123;
    private final static String DESCRIPTION = "Test description";

    @Mock
    private CarServiceImpl carService;

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
        when(carService.getCarById(ID)).thenReturn(carResponse);

        carController.getCarById(ID);

        verify(carService, times(1)).getCarById(ID);
    }

}
