package com.example.delivery.tracking.API.mapper;

import com.example.delivery.tracking.API.dto.request.DeliveryStatusHistoryRequestDto;
import com.example.delivery.tracking.API.dto.response.DeliveryStatusHistoryResponseDto;
import com.example.delivery.tracking.API.entity.Delivery;
import com.example.delivery.tracking.API.entity.DeliveryStatusHistory;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusHistoryMapper {

    public DeliveryStatusHistory toEntity(DeliveryStatusHistoryRequestDto dto, Delivery delivery) {
        if (dto == null) return null;

        return DeliveryStatusHistory.builder()
                .delivery(delivery)
                .oldStatus(dto.getOldStatus())
                .newStatus(dto.getNewStatus())
                .changedBy(dto.getChangedBy())
                .notes(dto.getNotes())
                .locationLatitude(dto.getLocationLatitude())
                .locationLongitude(dto.getLocationLongitude())
                .build();
    }

    public DeliveryStatusHistoryResponseDto toDto(DeliveryStatusHistory entity) {
        if (entity == null) return null;

        return DeliveryStatusHistoryResponseDto.builder()
                .id(entity.getId())
                .deliveryId(entity.getDelivery().getId())
                .oldStatus(entity.getOldStatus())
                .newStatus(entity.getNewStatus())
                .changedBy(entity.getChangedBy())
                .notes(entity.getNotes())
                .locationLatitude(entity.getLocationLatitude())
                .locationLongitude(entity.getLocationLongitude())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public void updateEntity(DeliveryStatusHistoryRequestDto dto, DeliveryStatusHistory entity, Delivery delivery) {
        if (dto == null || entity == null) return;

        entity.setDelivery(delivery);
        entity.setOldStatus(dto.getOldStatus());
        entity.setNewStatus(dto.getNewStatus());
        entity.setChangedBy(dto.getChangedBy());
        entity.setNotes(dto.getNotes());
        entity.setLocationLatitude(dto.getLocationLatitude());
        entity.setLocationLongitude(dto.getLocationLongitude());
    }

}
