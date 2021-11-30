package com.crm.dto.request;

import com.crm.exception.ErrorDict;

import com.crm.model.db.AddressEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerRequest {

    // Allowing polish numbers only without prefixes, dashes or white spaces
    private static final String PHONE_PATTERN = "^(45|5[0,1,3,7]|6[0,6,9]|7[2,3,8,9]|88)[0-9]{7}$";

    @NotBlank(message = ErrorDict.CUSTOMER_NAME_INVALID)
    @Size(min = 3, max = 30, message = ErrorDict.NAME_LENGTH_MUST_BETWEEN)
    private String name;

    @NotBlank(message = ErrorDict.CUSTOMER_SURNAME_INVALID)
    @Size(min = 3, max = 30, message = ErrorDict.SURNAME_LENGTH_MUST_BETWEEN)
    private String surname;

    @NotBlank(message = ErrorDict.CUSTOMER_PHONE_INVALID)
    @Pattern(regexp = PHONE_PATTERN, message = ErrorDict.PHONE_NUMBER_FORMAT_INVALID)
    private String phone;

    @Valid
    private AddressEntity address;
}
