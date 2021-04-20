package com.crm.model.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "car_types")
public class CarTypeEntity {

    @Id
    private long id;

    private String name;
}
