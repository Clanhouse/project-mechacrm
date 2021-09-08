package com.crm.repository;

import com.crm.model.db.CarTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarTypeRepository extends JpaRepository<CarTypeEntity, Long> {

    Optional<CarTypeEntity> findByNameIgnoreCase(String name);
}
