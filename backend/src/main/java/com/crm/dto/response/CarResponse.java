package com.crm.dto.response;

import com.crm.model.db.CarTypeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CarResponse {

    private Long id;
    private String vin;
    private String registrationNumber;
    private String brand;
    private String model;
    private Integer productionYear;
    private Integer mileage;
    private String description;
    private CarTypeEntity carTypeEntity;
}
