package com.crm.repository;

import com.crm.model.db.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Optional<CarEntity> findByRegistrationNumberIgnoreCase(final String registrationNumber);

    Optional<CarEntity> findByVinIgnoreCase(final String vin);
}
