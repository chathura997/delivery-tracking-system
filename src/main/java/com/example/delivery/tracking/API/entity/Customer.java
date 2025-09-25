package com.example.delivery.tracking.API.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^07[0-9]{8}$", message = "Invalid Sri Lankan phone number format")
    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    @Column(unique = true, length = 100)
    private String email;

    @Size(max = 500, message = "Default address must be less than 500 characters")
    @Column(name = "default_address", columnDefinition = "TEXT")
    private String defaultAddress;

    @DecimalMin(value = "-90.0", message = "Default latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Default latitude must be <= 90")
    @Column(name = "default_latitude", precision = 10, scale = 8)
    private java.math.BigDecimal defaultLatitude;

    @DecimalMin(value = "-180.0", message = "Default longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Default longitude must be <= 180")
    @Column(name = "default_longitude", precision = 11, scale = 8)
    private java.math.BigDecimal defaultLongitude;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Delivery> deliveries = new ArrayList<>();
}
