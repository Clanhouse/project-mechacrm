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
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@WithMockUser
@Transactional
class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    void shouldResponseEntityHasPageNumberEqualTo1() throws Exception {
        //when
        mvc.perform(get("/cars?page=1"))
                .andDo(print())
                //then
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
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.content", notNullValue()));
    }

    @Test
    void shouldReturnProperValuesAccordingToEntryData() throws Exception {
        //given
        CarEntity carEntity = CarEntity.builder()
                .brand("brand")
                .description("description")
                .model("model")
                .productionYear(2000)
                .mileage(1000)
                .registrationNumber("registrationNumber")
                .vin("vin")
                .build();

        carRepository.save(carEntity);
        //when
        mvc.perform(get("/cars"))
                .andDo(print())
                //then
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.content[0].vin", is("vin")))
                .andExpect(jsonPath("$.content[0].registrationNumber", is("registrationNumber")))
                .andExpect(jsonPath("$.content[0].brand", is("brand")))
                .andExpect(jsonPath("$.content[0].productionYear", is(2000)))
                .andExpect(jsonPath("$.content[0].mileage", is(1000)))
                .andExpect(jsonPath("$.content[0].description", is("description")));
    }
}