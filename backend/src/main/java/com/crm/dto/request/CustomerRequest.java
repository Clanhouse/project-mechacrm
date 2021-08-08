package com.crm.dto.request;

import com.crm.exception.ErrorDict;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank(message = ErrorDict.CUSTOMER_NAME_INVALID)
    @Min(3)
    @Max(30)
    private String name;
    @NotBlank(message = ErrorDict.CUSTOMER_SURNAME_INVALID)
    @Min(3)
    @Max(30)
    private String surname;
    @NotBlank(message = ErrorDict.CUSTOMER_PHONE_INVALID)
    private String phone;
    @NotBlank(message = ErrorDict.CUSTOMER_ADDRESS_INVALID)
    @Min(5)
    @Max(50)
    private String address;
}
