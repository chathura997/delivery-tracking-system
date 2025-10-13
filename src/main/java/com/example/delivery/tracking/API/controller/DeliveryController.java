package com.example.delivery.tracking.API.controller;

import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;
import com.example.delivery.tracking.API.entity.Driver;
import com.example.delivery.tracking.API.enums.DeliveryStatus;
import com.example.delivery.tracking.API.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    @PostMapping
    public ResponseEntity<DeliveryResponseDto> createDelivery(@Valid @RequestBody DeliveryRequestDto deliveryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.create(deliveryRequestDto));
    }
    @GetMapping
    public ResponseEntity<List<DeliveryResponseDto>> getAllDeliveries() {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto> getDeliveryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.getByID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto> updateDelivery(@PathVariable Long id,
                                                              @Valid @RequestBody DeliveryRequestDto deliveryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(deliveryService.update(deliveryRequestDto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{deliveryId}/auto-assign")
    public ResponseEntity<?> autoAssignDriver(@PathVariable Long deliveryId) {
        try {
            DeliveryResponseDto delivery = deliveryService.autoAssignDriver(deliveryId);
            return ResponseEntity.ok(delivery);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/nearby-drivers")
    public ResponseEntity<List<Driver>> findNearbyDrivers(
            @RequestParam BigDecimal latitude,
            @RequestParam BigDecimal longitude,
            @RequestParam(defaultValue = "10") int radiusKm) {
        List<Driver> drivers = deliveryService.findNearestAvailableDrivers(latitude, longitude, radiusKm);
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/validate-status-transition")
    public ResponseEntity<Map<String, Boolean>> validateStatusTransition(
            @RequestParam(required = false) DeliveryStatus from,
            @RequestParam DeliveryStatus to) {
        boolean canTransition = deliveryService.canTransitionStatus(from, to);
        return ResponseEntity.ok(Map.of("canTransition", canTransition));
    }

    @GetMapping("/driver/{driverId}/average-time")
    public ResponseEntity<Map<String, Object>> getDriverAverageTime(@PathVariable Long driverId) {
        double avgTime = deliveryService.calculateAverageDeliveryTime(driverId);
        return ResponseEntity.ok(Map.of(
                "driverId", driverId,
                "averageDeliveryTimeMinutes", avgTime,
                "formatted", String.format("%.1f minutes", avgTime)
        ));
    }
}
