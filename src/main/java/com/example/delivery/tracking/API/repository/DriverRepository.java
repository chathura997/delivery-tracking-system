package com.example.delivery.tracking.API.repository;

import com.example.delivery.tracking.API.entity.Driver;
import com.example.delivery.tracking.API.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    List<Driver> findByStatus (DriverStatus status);
}
