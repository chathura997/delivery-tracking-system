package com.example.delivery.tracking.API.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocationUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    @JsonBackReference
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    @JsonBackReference
    private Delivery delivery;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @DecimalMin(value = "0.0", message = "Accuracy must be >= 0")
    @DecimalMax(value = "10000.0", message = "Accuracy must be <= 10000 meters")
    @Column(precision = 8, scale = 2)
    private BigDecimal accuracy; // GPS accuracy in meters

    @DecimalMin(value = "0.0", message = "Speed must be >= 0")
    @DecimalMax(value = "300.0", message = "Speed must be <= 300 km/h")
    @Column(precision = 8, scale = 2)
    private BigDecimal speed; // Speed in km/h

    @DecimalMin(value = "0.0", message = "Heading must be >= 0")
    @DecimalMax(value = "360.0", message = "Heading must be <= 360 degrees")
    @Column(precision = 5, scale = 2)
    private BigDecimal heading; // Direction in degrees (0-360)

    @NotNull(message = "Recorded time is required")
    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
