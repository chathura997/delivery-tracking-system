package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;
import com.example.delivery.tracking.API.service.DeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Override
    public DeliveryResponseDto create(DeliveryRequestDto deliveryRequestDto) {
        return null;
    }

    @Override
    public List<DeliveryResponseDto> getAll() {
        return null;
    }

    @Override
    public DeliveryResponseDto getByID(Long aLong) {
        return null;
    }

    @Override
    public DeliveryResponseDto update(DeliveryRequestDto deliveryRequestDto, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
