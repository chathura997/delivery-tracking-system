package com.example.delivery.tracking.API.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStatusEvent {

    private Long deliveryId;
    private Long customerId;
    private String customerName;
    private Long driverId;
    private String driverName;
    private String oldStatus;
    private String newStatus;
    private String changedBy;
    private LocalDateTime timestamp;

}
