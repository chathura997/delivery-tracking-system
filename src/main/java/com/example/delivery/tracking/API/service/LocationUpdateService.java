package com.example.delivery.tracking.API.service;

import com.example.delivery.tracking.API.dto.request.LocationUpdateRequestDto;
import com.example.delivery.tracking.API.dto.response.LocationUpdateResponseDto;

import java.util.List;


public interface LocationUpdateService {
    LocationUpdateResponseDto create(LocationUpdateRequestDto dto);
    List<LocationUpdateResponseDto> getAll();
    LocationUpdateResponseDto getById(Long id);
    List<LocationUpdateResponseDto> getByDriver(Long driverId);
    List<LocationUpdateResponseDto> getByDelivery(Long deliveryId);
    LocationUpdateResponseDto update(Long id, LocationUpdateRequestDto dto);
    void delete(Long id);
}
