package com.example.delivery.tracking.API.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerRequestDto {
    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^07[0-9]{8}$", message = "Invalid Sri Lankan phone number format")
    private String phone;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @Size(max = 500, message = "Default address must be less than 500 characters")
    private String defaultAddress;

    @DecimalMin(value = "-90.0", message = "Default latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Default latitude must be <= 90")
    private BigDecimal defaultLatitude;

    @DecimalMin(value = "-180.0", message = "Default longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Default longitude must be <= 180")
    private BigDecimal defaultLongitude;

    @NotNull(message = "isActive cannot be null")
    private Boolean isActive = true;
}
