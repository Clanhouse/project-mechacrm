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
import static org.hamcrest.Matchers.nullValue;
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
    void shouldReturnCorrectResponseStatusWhenCallingCarWithExistingId_WhenDescriptionNotBlank() throws Exception {
        mvc.perform(get("/cars/1"))
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
    void shouldReturnCorrectResponseStatusWhenCallingCarWithExistingId_WhenDescriptionIsBlank() throws Exception {
        mvc.perform(get("/cars/4"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.vin", is("WBA86H90021859321")))
                .andExpect(jsonPath("$.registrationNumber", is("ELAS23")))
                .andExpect(jsonPath("$.brand", is("BMW")))
                .andExpect(jsonPath("$.model", is("M5")))
                .andExpect(jsonPath("$.productionYear", is(2020)))
                .andExpect(jsonPath("$.mileage", is(22500)))
                .andExpect(jsonPath("$.description", is("")))
                .andExpect(jsonPath("$.carTypeEntity.id", is(1)))
                .andExpect(jsonPath("$.carTypeEntity.name", is("Sedan")))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldReturnCorrectResponseStatusWhenCallingCarWithExistingId_WhenDescriptionIsNull() throws Exception {
        mvc.perform(get("/cars/6"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.vin", is("TMB1234FGH5678910")))
                .andExpect(jsonPath("$.registrationNumber", is("SK123F")))
                .andExpect(jsonPath("$.brand", is("Skoda")))
                .andExpect(jsonPath("$.model", is("Octavia")))
                .andExpect(jsonPath("$.productionYear", is(2010)))
                .andExpect(jsonPath("$.mileage", is(260000)))
                .andExpect(jsonPath("$.description", nullValue()))
                .andExpect(jsonPath("$.carTypeEntity.id", is(1)))
                .andExpect(jsonPath("$.carTypeEntity.name", is("Sedan")))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldReturnBadResponseStatusWhenCallingCarWithNonExistingId() throws Exception {
        mvc.perform(get("/cars/1000"))
                .andExpect(status().is(NOT_FOUND.value()));
    }
}