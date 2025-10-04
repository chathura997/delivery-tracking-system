package com.example.delivery.tracking.API.entity;

import com.example.delivery.tracking.API.enums.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery_status_history")
public class DeliveryStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_status")
    private DeliveryStatus oldStatus;

    @NotNull(message = "New status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false)
    private DeliveryStatus newStatus;

    @Size(max = 100, message = "Changed by field must be less than 100 characters")
    @Column(name = "changed_by", length = 100)
    private String changedBy; // driver name, admin, system, customer service

    @Size(max = 1000, message = "Notes must be less than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "location_latitude", precision = 10, scale = 8)
    private java.math.BigDecimal locationLatitude; // Where the status change happened

    @Column(name = "location_longitude", precision = 11, scale = 8)
    private java.math.BigDecimal locationLongitude; // Where the status change happened

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}