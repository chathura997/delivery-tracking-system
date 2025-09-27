package com.example.delivery.tracking.API.controller;

import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;
import com.example.delivery.tracking.API.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
