package com.crm.repository;

import com.crm.model.db.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Optional<CarEntity> findByRegistrationNumberIgnoreCase(final String registrationNumber);

    Optional<CarEntity> findByVinIgnoreCase(String vin);
}
