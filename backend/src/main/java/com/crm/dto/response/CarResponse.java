package com.crm.dto.response;

import com.crm.model.db.CarTypeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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