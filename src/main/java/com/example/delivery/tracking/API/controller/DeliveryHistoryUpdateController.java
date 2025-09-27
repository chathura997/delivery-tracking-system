package com.example.delivery.tracking.API.controller;

import com.example.delivery.tracking.API.dto.request.DeliveryStatusHistoryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryStatusHistoryResponseDto;
import com.example.delivery.tracking.API.service.DeliveryStatusHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/delivery-histories-update")
public class DeliveryHistoryUpdateController {

    private final DeliveryStatusHistoryService historyService;

    @PostMapping
    public ResponseEntity<DeliveryStatusHistoryResponseDto> create(@Valid @RequestBody DeliveryStatusHistoryRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historyService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryStatusHistoryResponseDto>> getAll() {
        return ResponseEntity.ok(historyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryStatusHistoryResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryStatusHistoryResponseDto> update(@PathVariable Long id,
                                                                   @Valid @RequestBody DeliveryStatusHistoryRequestDto dto) {
        return ResponseEntity.ok(historyService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        historyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
