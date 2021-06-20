package com.crm.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String phone;
    @NotNull
    private String address;
}
