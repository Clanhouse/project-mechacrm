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

import static com.crm.exception.ErrorDict.PAGINATED_EMPTY_PAGE;
import static com.crm.exception.ErrorDict.PAGINATED_EMPTY_SIZE;
import static com.crm.exception.ErrorDict.PAGINATED_INVALID_PAGE;
import static com.crm.exception.ErrorDict.PAGINATED_INVALID_SIZE;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
class CustomerControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnCorrectResponseStatusWhenCallingCustomerWithExistingId() throws Exception {
        mvc.perform(get("/customers/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldReturnBadResponseStatusWhenCallingCustomerWithNonExistingId() throws Exception {
        mvc.perform(get("/customers/1000"))
                .andExpect(status().is(NOT_FOUND.value()));
    }

    @Test
    void shouldReturnCorrectResponseStatusWhenCallingCustomersEndpoint() throws Exception {
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
    void shouldResponseEntityHasPageNumberEqualTo2WhenParamSizeEqualTo1() throws Exception {
        mvc.perform(get("/customers?page=1&size=1"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(1)));
    }

    @Test
    void shouldReturnErrorStatus400AndMessagePageCantBeEmpty() throws Exception {
        mvc.perform(get("/customers?page="))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_EMPTY_PAGE)));
    }

    @Test
    void shouldReturnErrorStatus400AndMessageSizeCantBeEmpty() throws Exception {
        mvc.perform(get("/customers?size="))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_EMPTY_SIZE)));
    }

    @Test
    void shouldReturnErrorStatus400AndMessagePageAndSizeCantBeEmpty() throws Exception {
        mvc.perform(get("/customers?size=&page="))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message", hasItem(PAGINATED_EMPTY_PAGE)))
                .andExpect(jsonPath("$.message", hasItem(PAGINATED_EMPTY_SIZE)));
    }

    @Test
    void shouldReturnErrorStatus400AndMessagePageParamInvalid() throws Exception {
        mvc.perform(get("/customers?page=-3"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_PAGE)));
    }

    @Test
    void shouldReturnErrorStatus400AndMessagePageParamTypeMismatch() throws Exception {
        mvc.perform(get("/customers?page=A"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_PAGE)));

        mvc.perform(get("/customers?page=*"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_PAGE)));
    }

    @Test
    void shouldReturnErrorStatus400AndMessageSizeParamTypeMismatch() throws Exception {
        mvc.perform(get("/customers?size=A"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_SIZE)));

        mvc.perform(get("/customers?size=*"))
                .andExpect(status().is(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message[0]", is(PAGINATED_INVALID_SIZE)));
    }
}
