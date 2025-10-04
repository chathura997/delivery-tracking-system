package com.example.delivery.tracking.API.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
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
