package com.example.delivery.tracking.API.controller;

import com.example.delivery.tracking.API.dto.request.DriverRequestDto;
import com.example.delivery.tracking.API.dto.response.DriverResponseDto;
import com.example.delivery.tracking.API.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/drivers")
public class DriverController {
    private final DriverService driverService;
    @PostMapping
    public ResponseEntity<DriverResponseDto> createDriver(@Valid @RequestBody DriverRequestDto driverRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.create(driverRequestDto));
    }
    @GetMapping
    public ResponseEntity<List<DriverResponseDto>> getAllDrivers(){
        return ResponseEntity.status(HttpStatus.OK).body(driverService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(driverService.getByID(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDto> updateDriver(@PathVariable Long id,@Valid @RequestBody DriverRequestDto driverRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(driverService.update(driverRequestDto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id){
        driverService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
