package java.com.crm.controller;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void shouldReturnAllCustomersByDefaultWithoutParameters(){
        // todo
    }


    @Test
    void shouldReturnAllCustomersBySizeByPageParameters(){
        // todo
    }

    @Test
    void shouldReturnErrorResponseParametersAreIllegal(){
        // todo
    }
}
