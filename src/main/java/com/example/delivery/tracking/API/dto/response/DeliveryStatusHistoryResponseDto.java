package com.example.delivery.tracking.API.dto.response;

import com.example.delivery.tracking.API.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
public class DeliveryStatusHistoryResponseDto {

    private Long id;
    private Long deliveryId;
    private DeliveryStatus oldStatus;
    private DeliveryStatus newStatus;
    private String changedBy;
    private String notes;
    private BigDecimal locationLatitude;
    private BigDecimal locationLongitude;
    private LocalDateTime createdAt;
}
