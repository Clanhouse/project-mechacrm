package com.crm.model.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    private Long id;

    private String vin;
    private String registrationNumber;
    private String brand;
    private String model;
    private Integer productionYear;
    private Integer mileage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_types_id")
    private CarTypeEntity carTypeEntity;
}
