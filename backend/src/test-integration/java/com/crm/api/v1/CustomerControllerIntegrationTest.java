package com.crm.api.v1;

import com.crm.App;
import com.crm.dto.request.CustomerRequest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerControllerIntegrationTest {

    private static final String NAME = "John";
    private static final String SURNAME = "Doe";
    private static final String PHONE = "+48 948302394";
    private static final String ADDRESS = "Test City";
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnCorrectResponseStatusWhenInsertingCustomer() throws Exception {
        CustomerRequest customer = new CustomerRequest();
        customer.setName(NAME);
        customer.setSurname(SURNAME);
        customer.setPhone(PHONE);
        customer.setAddress(ADDRESS);
        var gson = new Gson();
        String json = gson.toJson(customer);

        mvc.perform(post("/customers").contentType(APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isCreated());
    }
}