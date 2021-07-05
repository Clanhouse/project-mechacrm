package com.crm.api.v1;

import com.crm.App;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
@Transactional
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnCorrectResponseStatusWhenCallingAuthEndpoint() throws Exception {
        mvc.perform(post("/newAccount")
                .content("{\n" +
                        "  \"email\": \"TEST@gmail.com\",\n" +
                        "  \"login\": \"TEST123\",\n" +
                        "  \"password\": \"TEST123456\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.login", is("TEST123")));
    }
}
