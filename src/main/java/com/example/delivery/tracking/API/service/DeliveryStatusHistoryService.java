package com.example.delivery.tracking.API.service;

import com.example.delivery.tracking.API.dto.request.DeliveryStatusHistoryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryStatusHistoryResponseDto;

import java.util.List;

public interface DeliveryStatusHistoryService {

    DeliveryStatusHistoryResponseDto create(DeliveryStatusHistoryRequestDto dto);
    List<DeliveryStatusHistoryResponseDto> getAll();
    DeliveryStatusHistoryResponseDto getById(Long id);
    DeliveryStatusHistoryResponseDto update(Long id, DeliveryStatusHistoryRequestDto dto);
    void delete(Long id);
}
