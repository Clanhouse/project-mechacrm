package com.crm.model.db;


import com.sun.istack.NotNull;
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

    @NotNull
    private int vin;
    @NotNull
    private String model;

    @NotNull
    @Column(name = "production_year")
    private int productionYear;
    @NotNull
    private int type;


    @ManyToMany(mappedBy="cars")
    Set<CustomerEntity> cars = new HashSet<>();
}
