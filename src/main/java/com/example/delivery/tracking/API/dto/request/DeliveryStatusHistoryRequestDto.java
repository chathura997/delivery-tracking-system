package com.example.delivery.tracking.API.dto.request;

import com.example.delivery.tracking.API.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@RequiredArgsConstructor
public class DeliveryStatusHistoryRequestDto {

    private Long deliveryId; // Reference to Delivery entity
    private DeliveryStatus oldStatus;
    private DeliveryStatus newStatus;
    private String changedBy;
    private String notes;
    private BigDecimal locationLatitude;
    private BigDecimal locationLongitude;
}
