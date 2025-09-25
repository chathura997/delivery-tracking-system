package com.example.delivery.tracking.API.entity;

import com.example.delivery.tracking.API.enums.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;

    @NotBlank(message = "Pickup address is required")
    @Size(max = 500, message = "Pickup address must be less than 500 characters")
    @Column(name = "pickup_address", nullable = false, columnDefinition = "TEXT")
    private String pickupAddress;

    @NotBlank(message = "Delivery address is required")
    @Size(max = 500, message = "Delivery address must be less than 500 characters")
    @Column(name = "delivery_address", nullable = false, columnDefinition = "TEXT")
    private String deliveryAddress;

    @DecimalMin(value = "-90.0", message = "Pickup latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Pickup latitude must be <= 90")
    @Column(name = "pickup_latitude", precision = 10, scale = 8)
    private BigDecimal pickupLatitude;

    @DecimalMin(value = "-180.0", message = "Pickup longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Pickup longitude must be <= 180")
    @Column(name = "pickup_longitude", precision = 11, scale = 8)
    private BigDecimal pickupLongitude;

    @DecimalMin(value = "-90.0", message = "Delivery latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Delivery latitude must be <= 90")
    @Column(name = "delivery_latitude", precision = 10, scale = 8)
    private BigDecimal deliveryLatitude;

    @DecimalMin(value = "-180.0", message = "Delivery longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Delivery longitude must be <= 180")
    @Column(name = "delivery_longitude", precision = 11, scale = 8)
    private BigDecimal deliveryLongitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status = DeliveryStatus.PENDING;

    @Column(name = "estimated_delivery_time")
    private LocalDateTime estimatedDeliveryTime;

    @Column(name = "actual_pickup_time")
    private LocalDateTime actualPickupTime;

    @Column(name = "actual_delivery_time")
    private LocalDateTime actualDeliveryTime;

    @Size(max = 1000, message = "Delivery notes must be less than 1000 characters")
    @Column(name = "delivery_notes", columnDefinition = "TEXT")
    private String deliveryNotes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // One delivery can have multiple location updates
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LocationUpdate> locationUpdates = new ArrayList<>();

    // One delivery can have multiple status history records
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DeliveryStatusHistory> statusHistory = new ArrayList<>();

}
