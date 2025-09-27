package com.example.delivery.tracking.API.mapper;

import com.example.delivery.tracking.API.dto.request.DriverRequestDto;
import com.example.delivery.tracking.API.dto.response.DriverResponseDto;
import com.example.delivery.tracking.API.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public Driver toEntity(DriverRequestDto dto){
        if(dto == null){
            return null;
        }

        return  Driver.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .numberPlate(dto.getNumberPlate())
                .status(dto.getStatus())
                .build();
    }

    public DriverResponseDto toDto(Driver entity){
        if(entity == null) return null;

        return DriverResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .numberPlate(entity.getNumberPlate())
                .status(entity.getStatus())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public void updateEntity(DriverRequestDto dto, Driver entity) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setNumberPlate(dto.getNumberPlate());
        entity.setStatus(dto.getStatus());
    }
}
