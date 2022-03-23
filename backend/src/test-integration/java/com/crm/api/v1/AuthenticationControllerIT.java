package com.crm.api.v1;

import com.crm.App;
import com.crm.BaseIntegrationTest;
import com.crm.dto.request.AuthenticationRequest;
import com.crm.exception.InvalidCredentialsException;
import com.crm.exception.UserDisabledException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.crm.exception.ErrorDict.INVALID_CREDENTIALS;
import static com.crm.exception.ErrorDict.USER_DISABLED;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private AuthenticationRequest existedAccount;
    private AuthenticationRequest notExistedAccount;
    private AuthenticationRequest disabledAccount;

    @BeforeAll
    public void setUp() {
        existedAccount = new AuthenticationRequest("admin", "admin");
        notExistedAccount = new AuthenticationRequest("Trevor7657499", "exhausted");
        disabledAccount = new AuthenticationRequest("user3", "user3");
    }

    @Test
    void shouldReturn_Ok_StatusWhenCallingAuthEndpoint() throws Exception {
        mvc.perform(post("/auth")
                .content(mapper.writeValueAsString(existedAccount))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.token").hasJsonPath())
                .andExpect(jsonPath("$.refreshToken").hasJsonPath());
    }

    @Test
    void shouldReturn_Unauthorized_StatusWhenCallingAuthEndpointWithBadCredentials() throws Exception {
        mvc.perform(post("/auth")
                .content(mapper.writeValueAsString(notExistedAccount))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(UNAUTHORIZED.value()))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof InvalidCredentialsException))
                .andExpect(jsonPath("$.message[0]", is(INVALID_CREDENTIALS)));
    }

    @Test
    void shouldReturn_Forbidden_StatusWhenCallingAuthEndpointWithDisabledUser() throws Exception {
        mvc.perform(post("/auth")
                .content(mapper.writeValueAsString(disabledAccount))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(FORBIDDEN.value()))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof UserDisabledException))
                .andExpect(jsonPath("$.message[0]", is(USER_DISABLED)));
    }
}
