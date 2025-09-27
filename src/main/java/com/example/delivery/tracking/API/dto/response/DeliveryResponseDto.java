package com.example.delivery.tracking.API.dto.response;

import com.example.delivery.tracking.API.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
@Data
public class DeliveryResponseDto {

    private Long id;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String pickupAddress;
    private String deliveryAddress;
    private BigDecimal pickupLatitude;
    private BigDecimal pickupLongitude;
    private BigDecimal deliveryLatitude;
    private BigDecimal deliveryLongitude;
    private Long driverId;
    private String driverName;
    private String driverPhone;
    private DeliveryStatus status;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualPickupTime;
    private LocalDateTime actualDeliveryTime;
    private String deliveryNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int statusHistoryCount;
    private int locationUpdatesCount;
}
