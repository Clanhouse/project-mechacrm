package com.crm.model.db;


import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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
    private String phone;

    @NotNull
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customers_cars",
            joinColumns = @JoinColumn(name = "customers_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<CarEntity> cars = new HashSet<>();

}
