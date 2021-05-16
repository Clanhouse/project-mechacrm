package com.crm.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerResponse that = (CustomerResponse) o;
        return getName().equals(that.getName()) && getSurname().equals(that.getSurname()) && getPhone().equals(that.getPhone()) && getAddress().equals(that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getPhone(), getAddress());
    }
}
