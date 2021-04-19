package com.crm.model.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "car_entity")
public class CarEntity {

    @Id
    private int id;

    private String vin;
    private String registrationNumber;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarType carType;
}
