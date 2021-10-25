package com.crm.api.v1;

import com.crm.App;
import com.crm.BaseIntegrationTest;
import com.crm.exception.ElementAlreadyExistException;
import com.crm.exception.InvalidCredentialsException;
import com.crm.exception.TokenExpiredException;
import com.crm.exception.UserDisabledException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static com.crm.exception.ErrorDict.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class AuthenticationControllerIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturn_Ok_StatusWhenCallingAuthEndpoint() throws Exception {
        mvc.perform(post("/auth")
                .content("{\n" +
                        "  \"login\": \"admin\",\n" +
                        "  \"password\": \"admin\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.token").hasJsonPath())
                .andExpect(jsonPath("$.refreshToken").hasJsonPath());
    }

    @Test
    void shouldReturn_Unauthorized_StatusWhenCallingAuthEndpointWithBadCredentials() throws Exception {
        mvc.perform(post("/auth")
                .content("{\n" +
                        "  \"login\": \"admin22632\",\n" +
                        "  \"password\": \"admin1244\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(UNAUTHORIZED.value()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCredentialsException))
                .andExpect(jsonPath("$.message[0]", is(INVALID_CREDENTIALS)));
    }

    @Test
    void shouldReturn_Forbidden_StatusWhenCallingAuthEndpointWithDisabledUser() throws Exception {
        mvc.perform(post("/auth")
                .content("{\n" +
                        "  \"login\": \"user3\",\n" +
                        "  \"password\": \"user3\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(FORBIDDEN.value()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserDisabledException))
                .andExpect(jsonPath("$.message[0]", is(USER_DISABLED)));
    }

    // todo trzeba naprawić maila
    @Test
    void shouldReturn_OK_StatusWhenCallingSetUpEndpoint() throws Exception {
        mvc.perform(post("/sign-up")
                .content("{\n" +
                        "  \"email\": \"TEST@gmail.com\",\n" +
                        "  \"login\": \"Test123\",\n" +
                        "  \"password\": \"Test123456\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.login", is("Test123")))
                .andExpect(jsonPath("$.email", is("TEST@gmail.com")));
    }

    @Test
    void shouldReturn_Conflict_StatusWhenCallingSignUpEndpointWithExistedEmail() throws Exception {
        mvc.perform(post("/sign-up")
                .content("{\n" +
                        "  \"email\": \"admin@mail.com\",\n" +
                        "  \"login\": \"Adminek123\",\n" +
                        "  \"password\": \"Test123456\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(CONFLICT.value()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ElementAlreadyExistException))
                .andExpect(jsonPath("$.message[0]", is(ACCOUNT_ALREADY_EXIST_BY_EMAIL)));
    }

    @Test
    void shouldReturn_Conflict_StatusWhenCallingSignUpEndpointWithExistedLogin() throws Exception {
        mvc.perform(post("/sign-up")
                .content("{\n" +
                        "  \"email\": \"admin345@gmail.com\",\n" +
                        "  \"login\": \"admin\",\n" +
                        "  \"password\": \"Test123456\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(CONFLICT.value()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ElementAlreadyExistException))
                .andExpect(jsonPath("$.message[0]", is(ACCOUNT_ALREADY_EXIST_BY_LOGIN)));
    }

    @Test
    void shouldReturn_Ok_StatusWhenCallingNewActivateAccountEndpointWithNotActivatedAccount() throws Exception {
        mvc.perform(get("/activate/af1aca10-e04c-4ea5-a6ed-56a777c22d99"))
                .andExpect(status().is(OK.value()))
                .andExpect(jsonPath("$.login", is("user2")))
                .andExpect(jsonPath("$.email", is("user2@mail.com")));
    }


    @Test
    void shouldReturn_NotFound_StatusWhenCallingNewActivateAccountEndpointWithNotExistedAccount() throws Exception {
        mvc.perform(get("/activate/af55166"))
                .andExpect(status().is(FORBIDDEN.value()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof TokenExpiredException))
                .andExpect(jsonPath("$.message[0]", is(TOKEN_EXPIRED)));
    }
}
