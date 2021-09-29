package com.crm.api.v1;

import com.crm.App;
import com.crm.BaseIntegrationTest;
import com.crm.dto.request.CustomerRequest;
import com.crm.exception.ErrorDict;
import com.crm.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.crm.exception.ErrorDict.PAGINATED_EMPTY_PAGE;
import static com.crm.exception.ErrorDict.PAGINATED_EMPTY_SIZE;
import static com.crm.exception.ErrorDict.PAGINATED_INVALID_PAGE;
import static com.crm.exception.ErrorDict.PAGINATED_INVALID_SIZE;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
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
class CustomerControllerIT extends BaseIntegrationTest {

    private static final String NAME = "Janusz";
    private static final String SURNAME = "Nowak";
    private static final String PHONE = "600600600";
    private static final String ADDRESS = "Warszawska 20, Warszawa";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerRequest customerRequest;

    @BeforeEach
    public void setUp() {
        customerRequest = CustomerRequest.builder()
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .build();
    }

    @Test
    void shouldResponseWith_OK_WhenCallingCustomerWithExistingId() throws Exception {
        mvc.perform(get("/customers/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldResponseWith_NotFound_WhenCallingCustomerWithNonExistingId() throws Exception {
        final String expectedMessage = ErrorDict.CUSTOMER_NOT_FOUND;

        MvcResult mvcResult = mvc.perform(get("/customers/1000"))
                .andDo(print())
                .andExpect(status().is(NOT_FOUND.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(expectedMessage));
        assertEquals(1, errorResponse.getMessage().size());
    }

    @Test
    void shouldResponseWith_OK_WhenCallingCustomersEndpoint() throws Exception {
        mvc.perform(get("/customers"))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldResponseWithDefaultValuesWhenCallingCustomersEndpoint() throws Exception {
        mvc.perform(get("/customers"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.pageable.pageSize", is(20)));
    }

    @Test
    void shouldResponseEntityHasPageNumberEqualTo1AndParamSizeEqualTo1() throws Exception {
        mvc.perform(get("/customers?page=1&size=1"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(1)));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenPageIsEmpty() throws Exception {
        mvc.perform(get("/customers?page="))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_EMPTY_PAGE)));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenSizeIsEmpty() throws Exception {
        mvc.perform(get("/customers?size="))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_EMPTY_SIZE)));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenPageAndSizeAreEmpty() throws Exception {
        mvc.perform(get("/customers?size=&page="))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message", hasItem(PAGINATED_EMPTY_PAGE)))
                .andExpect(jsonPath("$.message", hasItem(PAGINATED_EMPTY_SIZE)));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenPageParamInvalid() throws Exception {
        mvc.perform(get("/customers?page=-3"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_PAGE)));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenPageParamTypeMismatch() throws Exception {
        mvc.perform(get("/customers?page=A"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_PAGE)));

        mvc.perform(get("/customers?page=*"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_PAGE)));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenSizeParamTypeMismatch() throws Exception {
        mvc.perform(get("/customers?size=A"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_SIZE)));

        mvc.perform(get("/customers?size=*"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_SIZE)));
    }

    @Test
    void shouldResponseWith_Created_WhenAddedCustomer() throws Exception {
        final String body = objectMapper.writeValueAsString(customerRequest);

        mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(CREATED.value()));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenBodyIsMissing() throws Exception {
        mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenBodyIsEmpty() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{}"))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        List<String> message = errorResponse.getMessage();

        assertAll(
                () -> assertTrue(message.contains(ErrorDict.CUSTOMER_NAME_INVALID)),
                () -> assertTrue(message.contains(ErrorDict.CUSTOMER_SURNAME_INVALID)),
                () -> assertTrue(message.contains(ErrorDict.CUSTOMER_PHONE_INVALID)),
                () -> assertTrue(message.contains(ErrorDict.CUSTOMER_ADDRESS_INVALID))
        );
    }

    @Test
    void shouldResponseWith_BadRequest_WhenNameIsBlank() throws Exception {
        customerRequest.setName("");
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_NAME_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenNameIsNull() throws Exception {
        customerRequest.setName(null);
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_NAME_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenNameIsShorterThan3Chars() throws Exception {
        customerRequest.setName("Ab");
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.NAME_LENGTH_MUST_BETWEEN));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenNameIsLongerThan30Chars() throws Exception {
        customerRequest.setName("a".repeat(31));
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.NAME_LENGTH_MUST_BETWEEN));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenSurnameIsBlank() throws Exception {
        customerRequest.setSurname("");
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_SURNAME_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenSurnameIsNull() throws Exception {
        customerRequest.setSurname(null);
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_SURNAME_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenSurnameIsShorterThan3Chars() throws Exception {
        customerRequest.setSurname("Al");
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.SURNAME_LENGTH_MUST_BETWEEN));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenSurnameIsLongerThan30Chars() throws Exception {
        customerRequest.setSurname("a".repeat(31));
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.SURNAME_LENGTH_MUST_BETWEEN));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenPhoneIsBlank() throws Exception {
        customerRequest.setPhone("");
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_PHONE_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenPhoneIsNull() throws Exception {
        customerRequest.setPhone(null);
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_PHONE_INVALID));
    }

    @ParameterizedTest
    @ValueSource(strings = {"66077088", "6607708809", "350660770", "660-770-880", "660 770 880", "+48660770880"})
    void shouldResponseWith_BadRequest_WhenPhoneDoesNotMatchToPattern(final String phoneNumber) throws Exception {
        customerRequest.setPhone(phoneNumber);
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.PHONE_NUMBER_FORMAT_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenAddressIsBlank() throws Exception {
        customerRequest.setAddress("");
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_ADDRESS_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenAddressIsNull() throws Exception {
        customerRequest.setAddress(null);
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.CUSTOMER_ADDRESS_INVALID));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenAddressIsShorterThan5Chars() throws Exception {
        customerRequest.setAddress("Abcd");
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.ADDRESS_LENGTH_MUST_BETWEEN));
    }

    @Test
    void shouldResponseWith_BadRequest_WhenAddressIsLongerThan50Chars() throws Exception {
        customerRequest.setAddress("a".repeat(51));
        final String body = objectMapper.writeValueAsString(customerRequest);

        MvcResult mvcResult = mvc.perform(post("/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andDo(print())
                .andExpect(status().is(BAD_REQUEST.value()))
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.getMessage().contains(ErrorDict.ADDRESS_LENGTH_MUST_BETWEEN));
    }
}
