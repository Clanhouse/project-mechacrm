package com.crm.api.v1;

import com.crm.App;
import com.crm.BaseIntegrationTest;
import com.crm.dto.request.CarRequest;
import com.crm.dto.response.CarResponse;
import com.crm.exception.ErrorDict;
import com.crm.exception.ErrorResponse;
import com.crm.model.db.CarTypeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
class CarControllerIT extends BaseIntegrationTest {

    private CarRequest carRequest;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        carRequest = CarRequest.builder()
                .vin("azwsxedc123456789")
                .registrationNumber("KR12345")
                .brand("Opel")
                .model("Astra")
                .productionYear(1951)
                .mileage(1)
                .description("")
                .carTypeEntity(CarTypeEntity.builder().name("Sedan").build())
                .build();
    }

    @Test
    void shouldResponseWith_OK_WhenCallingCarsEndpoint() throws Exception {
        mvc.perform(get("/cars"))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldResponseEntityHasPageNumberEqualTo1AndParamSizeEqualTo1() throws Exception {
        mvc.perform(get("/cars?page=1&size=1"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(1)));
    }

    @Test
    void shouldResponseWithDefaultValuesWhenCallingCarsEndpoint() throws Exception {
        mvc.perform(get("/cars"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(20)));
    }

    @Test
    void shouldResponseWith_OK_WhenCallingCarWithExistingId_WhenDescriptionNotBlank() throws Exception {
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
    void shouldResponseWith_OK_WhenCallingCarWithExistingId_WhenDescriptionIsBlank() throws Exception {
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
    void shouldResponseWith_OK_WhenCallingCarWithExistingId_WhenDescriptionIsNull() throws Exception {
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
    void shouldResponseWith_NotFound_WhenCallingCarWithNonExistingId() throws Exception {
        final String expectedMessage = ErrorDict.CAR_NOT_FOUND;

        MvcResult mvcResult = mvc.perform(get("/cars/1000"))
                .andDo(print())
                .andExpect(status().is(NOT_FOUND.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(expectedMessage));
        assertEquals(1, errorResponse.getMessage().size());
    }

    @Test
    void shouldResponseWith_OK_WhenCallingCarWithValidRegistrationNumber() throws Exception {
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
    void shouldResponseWith_NotFound_WhenCallingCarWithInvalidRegistrationNumber() throws Exception {
        final String expectedMessage = ErrorDict.REGISTRATION_NUMBER_NOT_FOUND;

        MvcResult mvcResult = mvc.perform(get("/cars/search?license-plate=KR"))
                .andDo(print())
                .andExpect(status().is(NOT_FOUND.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(expectedMessage));
        assertEquals(1, errorResponse.getMessage().size());
    }

    @Test
    void shouldResponseWith_NotFound_WhenCallingCarWithBlankRegistrationNumber() throws Exception {
        final String expectedMessage = ErrorDict.REGISTRATION_NUMBER_NOT_FOUND;

        MvcResult mvcResult = mvc.perform(get("/cars/search?license-plate="))
                .andDo(print())
                .andExpect(status().is(NOT_FOUND.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(expectedMessage));
        assertEquals(1, errorResponse.getMessage().size());
    }

    @SneakyThrows
    @Test
    void shouldResponseWith_NotFound_WhenVinIsInvalid() {
        final String expectedMessage = ErrorDict.VIN_LENGTH_INVALID;

        MvcResult mvcResult = mvc.perform(get("/cars/search?vin=vvvvv"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(expectedMessage));
        assertEquals(1, errorResponse.getMessage().size());
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_NotFound_WhenVinHasInvalidCharacters() {
        final String expectedMessage = ErrorDict.VIN_FORMAT_INVALID;
        final String vinWithInvalidCharacters = "vavaaav123456781q";

        MvcResult mvcResult = mvc.perform(get("/cars/search?vin=" + vinWithInvalidCharacters))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(expectedMessage));
        assertEquals(1, errorResponse.getMessage().size());
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_NotFound_WhenVinDoesNotExists() {
        final String expectedMessage = ErrorDict.VIN_NOT_FOUND;
        final String vinDoesNotExists = "a1w2e3r4t5y6u7g8b";

        MvcResult mvcResult = mvc.perform(get("/cars/search?vin=" + vinDoesNotExists))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(expectedMessage));
        assertEquals(1, errorResponse.getMessage().size());
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_Ok_WhenVinExists() {
        final String vinDoesExists = "TMB67890123456452";

        MvcResult mvcResult = mvc.perform(get("/cars/search?vin=" + vinDoesExists))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        CarResponse carResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CarResponse.class);

        assertEquals(carResponse.getVin(), vinDoesExists);
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenBodyIsMissing() {
        mvc.perform(post("/cars"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenBodyIsEmpty() {
        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        List<String> message = errorResponse.getMessage();

        assertTrue(message.contains(ErrorDict.CAR_VIN_INVALID));
        assertTrue(message.contains(ErrorDict.CAR_BRAND_INVALID));
        assertTrue(message.contains(ErrorDict.CAR_MODEL_INVALID));
        assertTrue(message.contains(ErrorDict.CAR_PRODUCTION_YEAR_INVALID));
        assertTrue(message.contains(ErrorDict.CAR_MILEAGE_INVALID));
        assertTrue(message.contains(ErrorDict.CAR_TYPE_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenVinIsBlank() {
        carRequest.setVin("");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_VIN_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenVinDoesNotMatchToPattern() {
        carRequest.setVin("VFTOQ123456789654");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.VIN_FORMAT_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenVinLengthDoesNotMatch() {
        carRequest.setVin("V6789654");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_VIN_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenRegistrationNumberIsShorterThenThreeChars() {
        carRequest.setRegistrationNumber("KR");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.REGISTRATION_NUMBER_LENGTH_MUST_BETWEEN));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenRegistrationNumberIsLongerThenTenChars() {
        carRequest.setRegistrationNumber("KR123456789");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.REGISTRATION_NUMBER_LENGTH_MUST_BETWEEN));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BedRequest_WhenRegistrationNumberDoesNotMatchToPattern() {
        carRequest.setRegistrationNumber("KR@1234");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.REGISTRATION_NUMBER_FORMAT_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenCarBrandIsBlank() {
        carRequest.setBrand("");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_BRAND_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenCarBrandIsShorterThanThreeChars() {
        carRequest.setBrand("Se");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.BRAND_LENGTH_MUST_BETWEEN));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenCarBrandIsLongerThanThirtyChars() {
        carRequest.setBrand("SedanSedanSedanSedanSedanSedanS");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.BRAND_LENGTH_MUST_BETWEEN));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenCarModelIsBlank() {
        carRequest.setModel("");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_MODEL_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenCarModelIsLongerThanThirtyChars() {
        carRequest.setModel("AstraAstraAstraAstraAstraAstraA");
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.MODEL_LENGTH_NOT_GREATER_THAN));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenProductionYearIsBlank() {
        carRequest.setProductionYear(null);
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_PRODUCTION_YEAR_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenProductionYearIsBefore1950() {
        carRequest.setProductionYear(1949);
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_PRODUCTION_YEAR_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenMileageIsBlank() {
        carRequest.setMileage(null);
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_MILEAGE_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenMileageIsNegative() {
        carRequest.setMileage(-1);
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_MILEAGE_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenDescriptionIsLongerThan250Chars() {
        carRequest.setDescription("a".repeat(251));
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.DESCRIPTION_LENGTH_NOT_GREATER_THAN));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_BadRequest_WhenCarTypeIsNull() {
        carRequest.setCarTypeEntity(null);
        final String body = objectMapper.writeValueAsString(carRequest);

        MvcResult mvcResult = mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CAR_TYPE_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWith_Created_WhenAddedCar() {
        final String body = objectMapper.writeValueAsString(carRequest);

        mvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
