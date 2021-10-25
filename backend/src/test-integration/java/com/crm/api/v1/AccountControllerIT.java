package com.crm.api.v1;

import com.crm.App;
import com.crm.BaseIntegrationTest;
import com.crm.dto.request.NewAccountRequest;
import com.crm.exception.ElementAlreadyExistException;
import com.crm.exception.TokenExpiredException;
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

import static com.crm.exception.ErrorDict.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private NewAccountRequest mailConflictAccount;
    private NewAccountRequest loginConflictAccount;
    private NewAccountRequest okNewAccount;

    @BeforeAll
    public void setUp(){
        mailConflictAccount = NewAccountRequest.builder()
                .email("admin@mail.com")
                .login("Test12345")
                .password("Adminek123")
                .build();

        loginConflictAccount = NewAccountRequest.builder()
                .email("admin345@gmail.com")
                .login("admin")
                .password("Test123456")
                .build();

        okNewAccount = NewAccountRequest.builder()
                .email("TEST135@gmail.com")
                .login("Test123")
                .password("Test123456")
                .build();
    }

    @Test
    void shouldReturn_Ok_WhenCallingActivateAccount_WithNotActivatedAccount() throws Exception {
        mvc.perform(get("/account/activate?token=af1aca10-e04c-4ea5-a6ed-56a777c22d99"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.login", is("user2")))
                .andExpect(jsonPath("$.email", is("user2@mail.com")));
    }

    @Test
    void shouldReturn_NotFound_WhenCallingActivateAccount_WithNotExistedAccount() throws Exception {
        mvc.perform(get("/account/activate?token=af55166"))
                .andExpect(status().is(FORBIDDEN.value()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof TokenExpiredException))
                .andExpect(jsonPath("$.message[0]", is(TOKEN_EXPIRED)));
    }

    @Test
    void shouldReturn_OK_StatusWhenCallingSignUpEndpoint() throws Exception {
        mvc.perform(post("/sign-up")
                        .content(mapper.writeValueAsString(okNewAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(OK.value()));
    }

    @Test
    void shouldReturn_Conflict_StatusWhenCallingSignUpEndpointWithExistedEmail() throws Exception {
        mvc.perform(post("/sign-up")
                        .content(mapper.writeValueAsString(mailConflictAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(CONFLICT.value()))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ElementAlreadyExistException))
                .andExpect(jsonPath("$.message[0]", is(ACCOUNT_ALREADY_EXIST_BY_EMAIL)));
    }

    @Test
    void shouldReturn_Conflict_StatusWhenCallingSignUpEndpointWithExistedLogin() throws Exception {
        mvc.perform(post("/sign-up")
                        .content(mapper.writeValueAsString(loginConflictAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(CONFLICT.value()))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ElementAlreadyExistException))
                .andExpect(jsonPath("$.message[0]", is(ACCOUNT_ALREADY_EXIST_BY_LOGIN)));
    }

    @Test
    void shouldReturn_Conflict_StatusWhenCallingSignUpEndpointWithMissingBody() throws Exception {
        mvc.perform(post("/sign-up")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(BAD_REQUEST.value()));
    }
}
