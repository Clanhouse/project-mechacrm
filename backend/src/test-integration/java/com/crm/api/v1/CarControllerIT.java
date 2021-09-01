package com.crm.api.v1;

import com.crm.App;
import com.crm.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
class CarControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnCorrectResponseStatusWhenCallingCarsEndpoint() throws Exception {
        mvc.perform(get("/cars"))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldResponseEntityHasPageNumberEqualTo2WhenParamSizeEqualTo1() throws Exception {
        mvc.perform(get("/cars?page=1&size=1"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(1)));
    }

    @Test
    void shouldResponseWithDefaultValues() throws Exception {
        mvc.perform(get("/cars"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(20)));
    }

    @Test
    void shouldReturnCorrectResponseStatusWhenCallingCarWithExistingId() throws Exception {
        mvc.perform(get("/cars/1"))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldReturnBadResponseStatusWhenCallingCarWithNonExistingId() throws Exception {
        mvc.perform(get("/cars/1000"))
                .andExpect(status().is(NOT_FOUND.value()));
    }

    @Test
    void shouldReturnCorrectResponseStatusWhenCallingCarWithValidRegistrationNumber() throws Exception {
        mvc.perform(get("/cars/search?license-plate=kR12pr"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.vin", is("JT3Z123KBW1589043")))
                .andExpect(jsonPath("$.registrationNumber", is("KR12PR")))
                .andExpect(jsonPath("$.brand", is("Toyota")))
                .andExpect(jsonPath("$.model", is("Avensis")))
                .andExpect(jsonPath("$.productionYear", is(2014)))
                .andExpect(jsonPath("$.mileage", is(123456)))
                .andExpect(jsonPath("$.description", is("Some description")))
                .andExpect(jsonPath("$.carTypeEntity.id", is(3)))
                .andExpect(jsonPath("$.carTypeEntity.name", is("Combi")))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldReturnBadResponseStatusWhenCallingCarWithInvalidRegistrationNumber() throws Exception {
        mvc.perform(get("/cars/search?license-plate=KR"))
                .andExpect(status().is(NOT_FOUND.value()));
    }

    @Test
    void shouldReturnBadResponseStatusWhenCallingCarWithBlankRegistrationNumber() throws Exception {
        mvc.perform(get("/cars/search?license-plate="))
                .andExpect(status().is(NOT_FOUND.value()));
    }
}