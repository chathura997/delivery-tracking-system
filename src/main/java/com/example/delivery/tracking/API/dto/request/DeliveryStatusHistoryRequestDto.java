package com.example.delivery.tracking.API.dto.request;

import com.example.delivery.tracking.API.enums.DeliveryStatus;
import lombok.*;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryStatusHistoryRequestDto {

    private Long deliveryId;
    private DeliveryStatus oldStatus;
    private DeliveryStatus newStatus;
    private String changedBy;
    private String notes;
    private BigDecimal locationLatitude;
    private BigDecimal locationLongitude;
}
