package com.example.delivery.tracking.API.mapper;

import com.example.delivery.tracking.API.dto.request.DeliveryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryResponseDto;
import com.example.delivery.tracking.API.entity.Customer;
import com.example.delivery.tracking.API.entity.Delivery;
import com.example.delivery.tracking.API.enums.DeliveryStatus;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {
    public Delivery toEntity(DeliveryRequestDto dto) {
        if (dto == null) return null;

        return Delivery.builder()
                .customer(dto.getCustomerId() != null ? Customer.builder()
                        .id(dto.getCustomerId())
                        .build() : null)
                .pickupAddress(dto.getPickupAddress())
                .deliveryAddress(dto.getDeliveryAddress())
                .pickupLatitude(dto.getPickupLatitude())
                .pickupLongitude(dto.getPickupLongitude())
                .deliveryLatitude(dto.getDeliveryLatitude())
                .deliveryLongitude(dto.getDeliveryLongitude())
                .deliveryNotes(dto.getDeliveryNotes())
                .estimatedDeliveryTime(dto.getEstimatedDeliveryTime())
                .status(dto.getDeliveryStatus() != null ? dto.getDeliveryStatus() : DeliveryStatus.PENDING)
                .build();
    }

    public DeliveryResponseDto toDto(Delivery entity) {
        if (entity == null) return null;

        return DeliveryResponseDto.builder()
                .id(entity.getId())
                .customerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null)
                .pickupAddress(entity.getPickupAddress())
                .deliveryAddress(entity.getDeliveryAddress())
                .pickupLatitude(entity.getPickupLatitude())
                .pickupLongitude(entity.getPickupLongitude())
                .deliveryLatitude(entity.getDeliveryLatitude())
                .deliveryLongitude(entity.getDeliveryLongitude())
                .deliveryNotes(entity.getDeliveryNotes())
                .estimatedDeliveryTime(entity.getEstimatedDeliveryTime())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntity(DeliveryRequestDto dto, Delivery entity) {
        if (dto == null || entity == null) return;
        if (dto.getCustomerId() != null) {
            entity.setCustomer(Customer.builder().id(dto.getCustomerId()).build());
        }
        entity.setPickupAddress(dto.getPickupAddress());
        entity.setDeliveryAddress(dto.getDeliveryAddress());
        entity.setPickupLatitude(dto.getPickupLatitude());
        entity.setPickupLongitude(dto.getPickupLongitude());
        entity.setDeliveryLatitude(dto.getDeliveryLatitude());
        entity.setDeliveryLongitude(dto.getDeliveryLongitude());
        entity.setDeliveryNotes(dto.getDeliveryNotes());
        entity.setEstimatedDeliveryTime(dto.getEstimatedDeliveryTime());
    }
}
