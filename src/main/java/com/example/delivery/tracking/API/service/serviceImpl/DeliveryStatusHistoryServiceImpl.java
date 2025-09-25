package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.DeliveryStatusHistoryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryStatusHistoryResponseDto;
import com.example.delivery.tracking.API.service.DeliveryStatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryStatusHistoryServiceImpl implements DeliveryStatusHistoryService {
    @Override
    public DeliveryStatusHistoryResponseDto create(DeliveryStatusHistoryRequestDto deliveryStatusHistoryRequestDto) {
        return null;
    }

    @Override
    public List<DeliveryStatusHistoryResponseDto> getAll() {
        return null;
    }

    @Override
    public DeliveryStatusHistoryResponseDto getByID(Long aLong) {
        return null;
    }

    @Override
    public DeliveryStatusHistoryResponseDto update(DeliveryStatusHistoryRequestDto deliveryStatusHistoryRequestDto, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
