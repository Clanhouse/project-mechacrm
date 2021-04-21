package com.crm.model.db;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private int phone;

    @NotNull
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customers_cars",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    Set<CarEntity> cars = new HashSet<>();

}
