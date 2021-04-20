package com.crm.model.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "cars")
public class CarEntity {

    @Id
    private long id;

    private String vin;
    private String registrationNumber;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_types_id")
    private CarTypeEntity carTypeEntity;
}
