package com.example.delivery.tracking.API.service;

import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;

public interface DeliveryService extends GenericCrudService<DeliveryResponseDto, DeliveryRequestDto,Long>{
}
