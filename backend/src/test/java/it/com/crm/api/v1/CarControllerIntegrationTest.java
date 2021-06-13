package it.com.crm.api.v1;

import com.crm.App;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnCorrectResponseWhenCallingCarsEndpointFirstValue() throws Exception {
        mvc.perform(get("/cars"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.content[0].vin", is("testVin")))
                .andExpect(jsonPath("$.content[0].registrationNumber", is("EL00000")))
                .andExpect(jsonPath("$.content[0].brand", is("Mercedes-Benz")))
                .andExpect(jsonPath("$.content[0].productionYear", is(2021)))
                .andExpect(jsonPath("$.content[0].mileage", is(10000)))
                .andExpect(jsonPath("$.content[0].description", is("TestData")));
    }

    @Test
    void shouldReturnCorrectResponseWhenCallingCarsEndpointSecondValue() throws Exception {
        mvc.perform(get("/cars"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.content[1].vin", is("testVin2")))
                .andExpect(jsonPath("$.content[1].registrationNumber", is("EL00001")))
                .andExpect(jsonPath("$.content[1].brand", is("Mercedes-Benz")))
                .andExpect(jsonPath("$.content[1].productionYear", is(2021)))
                .andExpect(jsonPath("$.content[1].mileage", is(10000)))
                .andExpect(jsonPath("$.content[1].description", is("TestData2")));
    }
    

    @Test
    void shouldResponseEntityHasPageNumberEqualTo2WhenParamSizeEqualTo1() throws Exception {
        mvc.perform(get("/cars?page=1&size=1"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(1)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.content", notNullValue()));
    }

    @Test
    void shouldResponseWithDefaultPageSize20() throws Exception {
        mvc.perform(get("/cars?page=1"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(20)))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.content", notNullValue()));
    }

    @Test
    void shouldResponseWithDefaultPage0() throws Exception {
        mvc.perform(get("/cars?size=10"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(10)))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.content", notNullValue()));
    }
}