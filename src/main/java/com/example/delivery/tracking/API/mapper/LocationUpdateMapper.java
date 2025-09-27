package com.example.delivery.tracking.API.mapper;

import com.example.delivery.tracking.API.dto.request.LocationUpdateRequestDto;
import com.example.delivery.tracking.API.dto.response.LocationUpdateResponseDto;
import com.example.delivery.tracking.API.entity.Delivery;
import com.example.delivery.tracking.API.entity.Driver;
import com.example.delivery.tracking.API.entity.LocationUpdate;
import org.springframework.stereotype.Component;

@Component
public class LocationUpdateMapper {

    public LocationUpdate toEntity(LocationUpdateRequestDto dto, Driver driver, Delivery delivery) {
        if (dto == null) return null;

        return LocationUpdate.builder()
                .driver(driver)
                .delivery(delivery)
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .accuracy(dto.getAccuracy())
                .speed(dto.getSpeed())
                .heading(dto.getHeading())
                .recordedAt(dto.getRecordedAt())
                .build();
    }

    public LocationUpdateResponseDto toDto(LocationUpdate entity) {
        if (entity == null) return null;

        return LocationUpdateResponseDto.builder()
                .id(entity.getId())
                .driverId(entity.getDriver() != null ? entity.getDriver().getId() : null)
                .deliveryId(entity.getDelivery() != null ? entity.getDelivery().getId() : null)
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .accuracy(entity.getAccuracy())
                .speed(entity.getSpeed())
                .heading(entity.getHeading())
                .recordedAt(entity.getRecordedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public void updateEntity(LocationUpdateRequestDto dto, LocationUpdate entity, Driver driver, Delivery delivery) {
        if (dto == null || entity == null) return;

        entity.setDriver(driver);
        entity.setDelivery(delivery);
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAccuracy(dto.getAccuracy());
        entity.setSpeed(dto.getSpeed());
        entity.setHeading(dto.getHeading());
        entity.setRecordedAt(dto.getRecordedAt());
    }
}
