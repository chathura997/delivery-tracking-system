package com.example.delivery.tracking.API.controller;

import com.example.delivery.tracking.API.dto.request.LocationUpdateRequestDto;
import com.example.delivery.tracking.API.dto.response.LocationUpdateResponseDto;
import com.example.delivery.tracking.API.service.LocationUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/Locations-update")
public class LocationUpdateController {
    private final LocationUpdateService locationUpdateService;

    @PostMapping
    public ResponseEntity<LocationUpdateResponseDto> create(@Valid @RequestBody LocationUpdateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationUpdateService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<LocationUpdateResponseDto>> getAll() {
        return ResponseEntity.ok(locationUpdateService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationUpdateResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(locationUpdateService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationUpdateResponseDto> update(@PathVariable Long id,
                                                            @Valid @RequestBody LocationUpdateRequestDto dto) {
        return ResponseEntity.ok(locationUpdateService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationUpdateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<LocationUpdateResponseDto>> getByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(locationUpdateService.getByDriver(driverId));
    }

    @GetMapping("/delivery/{deliveryId}")
    public ResponseEntity<List<LocationUpdateResponseDto>> getByDelivery(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(locationUpdateService.getByDelivery(deliveryId));
    }
}
