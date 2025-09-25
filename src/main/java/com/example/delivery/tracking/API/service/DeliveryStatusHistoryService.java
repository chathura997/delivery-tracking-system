package com.example.delivery.tracking.API.service;

import com.example.delivery.tracking.API.dto.request.DeliveryStatusHistoryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryStatusHistoryResponseDto;

public interface DeliveryStatusHistoryService extends GenericCrudService<DeliveryStatusHistoryResponseDto, DeliveryStatusHistoryRequestDto,Long> {
}
