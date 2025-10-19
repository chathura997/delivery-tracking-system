package com.example.delivery.tracking.API.repository;

import com.example.delivery.tracking.API.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    List<Delivery> findByDriverId(Long driverId);
}
