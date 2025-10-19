package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.event.DeliveryStatusEvent;
import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;
import com.example.delivery.tracking.API.entity.Delivery;
import com.example.delivery.tracking.API.entity.Driver;
import com.example.delivery.tracking.API.enums.DeliveryStatus;
import com.example.delivery.tracking.API.enums.DriverStatus;
import com.example.delivery.tracking.API.mapper.DeliveryMapper;
import com.example.delivery.tracking.API.repository.DeliveryRepository;
import com.example.delivery.tracking.API.repository.DriverRepository;
import com.example.delivery.tracking.API.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryMapper deliveryMapper;
    private final DeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final DriverRepository driverRepository;

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

    @Override
    public DeliveryResponseDto autoAssignDriver(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + deliveryId));

        if (delivery.getStatus() != DeliveryStatus.PENDING) {
            throw new RuntimeException("Delivery is not pending. Current status: " + delivery.getStatus());
        }

        List<Driver> availableDrivers = driverRepository.findByStatus(DriverStatus.AVAILABLE);
        if (availableDrivers.isEmpty()) {
            throw new RuntimeException("No available drivers found");
        }

        // Assign first available driver
        Driver driver = availableDrivers.get(0);
        delivery.setDriver(driver);
        delivery.setStatus(DeliveryStatus.ASSIGNED);
        driver.setStatus(DriverStatus.BUSY);

        deliveryRepository.save(delivery);
        driverRepository.save(driver);

        // Send Kafka event
        DeliveryStatusEvent event = new DeliveryStatusEvent(
                delivery.getId(),
                delivery.getCustomer().getId(),
                delivery.getCustomer().getName(),
                driver.getId(),
                driver.getName(),
                DeliveryStatus.PENDING.toString(),
                DeliveryStatus.ASSIGNED.toString(),
                "System Auto-Assignment",
                LocalDateTime.now()
        );
        kafkaTemplate.send("delivery-status-updates", event);
        return deliveryMapper.toDto(delivery);
    }

    @Override
    public List<Driver> findNearestAvailableDrivers(BigDecimal lat, BigDecimal lng, int radiusKm) {
        return null;
    }

    @Override
    public boolean canTransitionStatus(DeliveryStatus from, DeliveryStatus to) {
        if (from == null) return to == DeliveryStatus.PENDING;

        return switch (from) {
            case PENDING -> to == DeliveryStatus.ASSIGNED || to == DeliveryStatus.CANCELLED;
            case ASSIGNED -> to == DeliveryStatus.PICKED_UP || to == DeliveryStatus.CANCELLED;
            case PICKED_UP -> to == DeliveryStatus.ON_ROUTE || to == DeliveryStatus.CANCELLED;
            case ON_ROUTE -> to == DeliveryStatus.DELIVERED || to == DeliveryStatus.CANCELLED;
            case DELIVERED, CANCELLED -> false;
        };
    }

    @Override
    public double calculateAverageDeliveryTime(Long driverId) {
        List<Delivery> completedDeliveries = deliveryRepository.findByDriverId(driverId)
                .stream()
                .filter(d -> d.getStatus() == DeliveryStatus.DELIVERED)
                .filter(d -> d.getActualPickupTime() != null && d.getActualDeliveryTime() != null)
                .collect(Collectors.toList());

        if (completedDeliveries.isEmpty()) {
            return 0.0;
        }

        long totalMinutes = completedDeliveries.stream()
                .mapToLong(d -> Duration.between(d.getActualPickupTime(), d.getActualDeliveryTime()).toMinutes())
                .sum();

        return (double) totalMinutes / completedDeliveries.size();
    }
}

