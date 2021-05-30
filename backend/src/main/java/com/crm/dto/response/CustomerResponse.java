package com.crm.dto.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CustomerResponse {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String address;
}
