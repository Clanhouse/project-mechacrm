package com.crm.dto.request;

import com.crm.exception.ErrorDict;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotEmpty(message = ErrorDict.CUSTOMER_NAME_INVALID)
    private String name;
    @NotEmpty(message = ErrorDict.CUSTOMER_SURNAME_INVALID)
    private String surname;
    @NotEmpty(message = ErrorDict.CUSTOMER_PHONE_INVALID)
    private String phone;
    @NotEmpty(message = ErrorDict.CUSTOMER_ADDRESS_INVALID)
    private String address;
}
