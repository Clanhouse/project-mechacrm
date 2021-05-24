package java.com.crm.dto;

import com.crm.dto.mapper.CustomerMapper;
import com.crm.dto.response.CustomerResponse;
import com.crm.model.db.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class CustomerMapperTest {

    private final CustomerMapper mapper;
    private CustomerResponse customerResponse;
    private CustomerEntity customerEntity;
    private static final Long ID = 1L;
    private static final String NAME = "test";
    private static final String SURNAME = "test";
    private static final String PHONE = "+48 111111111";
    private static final String ADDRESS = "test";

    @BeforeAll
    void setUp() {
        customerResponse = CustomerResponse.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .build();

        customerEntity = CustomerEntity.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .phone(PHONE)
                .address(ADDRESS)
                .build();
    }

    @Test
    void shouldReturnMappedCustomerToCustomerDto() {
        CustomerResponse customerResponse = mapper.convertToDto(this.customerEntity);

    }

    @Test
    void shouldReturnMappedCustomerDtoToCustomer() {
        CustomerEntity customerEntity = mapper.convertToEntity(this.customerResponse);
    }

}
