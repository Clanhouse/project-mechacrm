package com.crm.model.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_types")
public class CarTypeEntity {

    @Id
    private Long id;

    private String name;
}
