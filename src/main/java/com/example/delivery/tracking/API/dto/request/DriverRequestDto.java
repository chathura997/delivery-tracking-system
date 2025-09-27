package com.example.delivery.tracking.API.dto.request;

import com.example.delivery.tracking.API.enums.DriverStatus;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DriverRequestDto {
    @NotBlank(message = "Driver name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^07[0-9]{8}$", message = "Invalid Sri Lankan phone number format")
    private String phone;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @Size(max = 20, message = "Number plate must be less than 20 characters")
    private String numberPlate;

    private DriverStatus status;

}
