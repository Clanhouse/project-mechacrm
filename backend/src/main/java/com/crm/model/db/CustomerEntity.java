package com.crm.model.db;


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
@Table(name ="customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private int phone;
    private String address;

     @ManyToMany(cascade=CascadeType.ALL)
             @JoinTable(
                     name="customers_cars",
                     joinColumns = @JoinColumn(name = "customer_id"),
                     inverseJoinColumns = @JoinColumn(name="car_id")
             )
     Set<CarEntity> cars = new HashSet<>();

}
