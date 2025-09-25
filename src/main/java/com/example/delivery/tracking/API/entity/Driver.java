package com.example.delivery.tracking.API.entity;


import com.example.delivery.tracking.API.enums.DriverStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Driver name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^07[0-9]{8}$", message = "Invalid Sri Lankan phone number format")
    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Size(max = 100, message = "Email must be less than 100 characters")
    @Column(length = 100)
    private String email;

    @Size(max = 20, message = "Number plate must be less than 20 characters")
    @Column(name = "number_plate", length = 20)
    private String numberPlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status = DriverStatus.AVAILABLE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // One driver can have multiple deliveries
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Delivery> deliveries = new ArrayList<>();

    // One driver can have multiple location updates
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LocationUpdate> locationUpdates = new ArrayList<>();
}
