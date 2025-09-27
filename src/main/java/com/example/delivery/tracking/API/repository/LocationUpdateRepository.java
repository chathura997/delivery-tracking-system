package com.example.delivery.tracking.API.repository;

import com.example.delivery.tracking.API.entity.LocationUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationUpdateRepository extends JpaRepository<LocationUpdate,Long> {

    List<LocationUpdate> findByDriverId(Long driverId);
    List<LocationUpdate> findByDeliveryId(Long deliveryId);
}
