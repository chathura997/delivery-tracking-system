package com.example.delivery.tracking.API.dto.response;

import com.example.delivery.tracking.API.enums.DriverStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
@Builder
public class DriverResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String numberPlate;
    private DriverStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int totalDeliveries;
    private int activeDeliveries;
}
