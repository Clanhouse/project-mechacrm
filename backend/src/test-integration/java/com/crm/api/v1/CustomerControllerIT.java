package com.crm.api.v1;

import com.crm.App;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
class CustomerControllerIT {

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
}
