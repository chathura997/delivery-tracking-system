package com.example.delivery.tracking.API.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
@Data
public class DeliveryRequestDto {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Pickup address is required")
    @Size(max = 500, message = "Pickup address must be less than 500 characters")
    private String pickupAddress;

    @NotBlank(message = "Delivery address is required")
    @Size(max = 500, message = "Delivery address must be less than 500 characters")
    private String deliveryAddress;

    @DecimalMin(value = "-90.0", message = "Pickup latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Pickup latitude must be <= 90")
    private BigDecimal pickupLatitude;

    @DecimalMin(value = "-180.0", message = "Pickup longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Pickup longitude must be <= 180")
    private BigDecimal pickupLongitude;

    @DecimalMin(value = "-90.0", message = "Delivery latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Delivery latitude must be <= 90")
    private BigDecimal deliveryLatitude;

    @DecimalMin(value = "-180.0", message = "Delivery longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Delivery longitude must be <= 180")
    private BigDecimal deliveryLongitude;

    @Size(max = 1000, message = "Delivery notes must be less than 1000 characters")
    private String deliveryNotes;

    private LocalDateTime estimatedDeliveryTime;
}
