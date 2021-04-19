package com.crm.model.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "car_type")
public class CarType {

    @Id
    private int id;

    private String name;
}
