package com.crm.api.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnResponseEntityPageCarResponse() throws Exception {
        mvc.perform(get("/cars?page=1"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)));

    }

}