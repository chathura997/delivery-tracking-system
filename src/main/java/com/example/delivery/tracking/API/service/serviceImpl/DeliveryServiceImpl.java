package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.event.DeliveryStatusEvent;
import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;
import com.example.delivery.tracking.API.entity.Delivery;
import com.example.delivery.tracking.API.mapper.DeliveryMapper;
import com.example.delivery.tracking.API.repository.DeliveryRepository;
import com.example.delivery.tracking.API.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryMapper deliveryMapper;
    private final DeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public DeliveryResponseDto create(DeliveryRequestDto deliveryRequestDto) {
        Delivery delivery = deliveryMapper.toEntity(deliveryRequestDto);

        Delivery saved = deliveryRepository.save(delivery);

        DeliveryStatusEvent event = new DeliveryStatusEvent(
                saved.getId(),
                saved.getCustomer().getId(),
                saved.getCustomer().getName(),
                null,
                null,
                null,
                saved.getStatus().toString(),
                "System",
                LocalDateTime.now()
        );
        kafkaTemplate.send("delivery-status-updates", event);
        return deliveryMapper.toDto(saved);
    }

    @Override
    public List<DeliveryResponseDto> getAll() {
        return deliveryRepository.findAll()
                .stream()
                .map(deliveryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryResponseDto getByID(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
        return deliveryMapper.toDto(delivery);
    }

    @Override
    public DeliveryResponseDto update(DeliveryRequestDto deliveryRequestDto, Long id) {
        Delivery existingDelivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));

        String oldStatus = existingDelivery.getStatus().toString();

        deliveryMapper.updateEntity(deliveryRequestDto, existingDelivery);

        Delivery updatedDelivery = deliveryRepository.save(existingDelivery);

        DeliveryStatusEvent event = new DeliveryStatusEvent(
                updatedDelivery.getId(),
                updatedDelivery.getCustomer().getId(),
                updatedDelivery.getCustomer().getName(),
                updatedDelivery.getDriver() != null ? updatedDelivery.getDriver().getId() : null,
                updatedDelivery.getDriver() != null ? updatedDelivery.getDriver().getName() : null,
                oldStatus,
                updatedDelivery.getStatus().toString(),
                "System",
                LocalDateTime.now()
        );

        kafkaTemplate.send("delivery-status-updates", event);

        return deliveryMapper.toDto(updatedDelivery);
    }

    @Override
    public void delete(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
        deliveryRepository.delete(delivery);
    }
}

