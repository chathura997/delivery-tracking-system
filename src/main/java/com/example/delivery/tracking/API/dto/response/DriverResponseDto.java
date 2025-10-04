package com.example.delivery.tracking.API.dto.response;

import com.example.delivery.tracking.API.enums.DriverStatus;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
