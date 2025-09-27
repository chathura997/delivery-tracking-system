package com.example.delivery.tracking.API.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
@Builder
public class LocationUpdateResponseDto {
    private Long id;
    private Long driverId;
    private Long deliveryId;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal accuracy;
    private BigDecimal speed;
    private BigDecimal heading;

    private LocalDateTime recordedAt;
    private LocalDateTime createdAt;
}
