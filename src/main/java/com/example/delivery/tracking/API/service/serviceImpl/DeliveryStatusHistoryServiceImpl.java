package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.DeliveryStatusHistoryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryStatusHistoryResponseDto;
import com.example.delivery.tracking.API.entity.Delivery;
import com.example.delivery.tracking.API.entity.DeliveryStatusHistory;
import com.example.delivery.tracking.API.mapper.DeliveryStatusHistoryMapper;
import com.example.delivery.tracking.API.repository.DeliveryRepository;
import com.example.delivery.tracking.API.repository.DeliveryStatusHistoryRepository;
import com.example.delivery.tracking.API.service.DeliveryStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryStatusHistoryServiceImpl implements DeliveryStatusHistoryService {

    private final DeliveryStatusHistoryMapper mapper;
    private final DeliveryStatusHistoryRepository historyRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryStatusHistoryResponseDto create(DeliveryStatusHistoryRequestDto dto) {
        Delivery delivery = deliveryRepository.findById(dto.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + dto.getDeliveryId()));

        DeliveryStatusHistory history = mapper.toEntity(dto, delivery);
        return mapper.toDto(historyRepository.save(history));
    }

    @Override
    public List<DeliveryStatusHistoryResponseDto> getAll() {
        return historyRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryStatusHistoryResponseDto getById(Long id) {
        DeliveryStatusHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery history not found with id: " + id));
        return mapper.toDto(history);
    }

    @Override
    public DeliveryStatusHistoryResponseDto update(Long id, DeliveryStatusHistoryRequestDto dto) {
        DeliveryStatusHistory existing = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery history not found with id: " + id));

        Delivery delivery = deliveryRepository.findById(dto.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + dto.getDeliveryId()));

        mapper.updateEntity(dto, existing, delivery);

        DeliveryStatusHistory updated = historyRepository.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        DeliveryStatusHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery history not found with id: " + id));
        historyRepository.delete(history);
    }
}
