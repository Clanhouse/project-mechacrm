package com.crm.model.db;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int vin;
    private String model;

    @Column(name = "production_year")
    private int productionYear;

    private int type;


    @ManyToMany(mappedBy="cars")
    Set<CustomerEntity> cars = new HashSet<>();
}
