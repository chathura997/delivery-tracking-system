package com.example.delivery.tracking.API.service;

import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;
import com.example.delivery.tracking.API.entity.Driver;

import java.math.BigDecimal;
import java.util.List;

public interface DeliveryService extends GenericCrudService<DeliveryResponseDto, DeliveryRequestDto,Long>{

//    public List<Driver> findNearestAvailableDrivers(BigDecimal lat, BigDecimal lng, int radiusKm);
//    public DeliveryResponseDto autoAssignDriver(Long deliveryId);

}
