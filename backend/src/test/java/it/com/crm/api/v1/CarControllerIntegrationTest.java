package it.com.crm.api.v1;

import com.crm.App;
import com.crm.model.db.CarEntity;
import com.crm.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    void shouldResponseEntityHasPageNumberEqualTo1() throws Exception {
        mvc.perform(get("/cars?page=1"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)));
    }

    @Test
    void shouldResponseEntityHasPageNumberEqualTo2WhenParamSizeEqualTo1() throws Exception {
        //given
        CarEntity carEntity = new CarEntity();
        carEntity.setBrand("testBrand");
        carRepository.save(carEntity);
        CarEntity carEntity2 = new CarEntity();
        carEntity.setBrand("testBrand2");
        carRepository.save(carEntity2);
        //when
        mvc.perform(get("/cars?page=1&size=1"))
                .andDo(print())
                //then
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.content", notNullValue()));
    }
}