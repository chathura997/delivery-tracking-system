package com.example.delivery.tracking.API.mapper;

import com.example.delivery.tracking.API.dto.request.CustomerRequestDto;
import com.example.delivery.tracking.API.dto.response.CustomerResponseDto;
import com.example.delivery.tracking.API.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

     public Customer toEntity(CustomerRequestDto dto) {
        if (dto == null) return null;

        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .isActive(dto.getIsActive())
                .defaultAddress(dto.getDefaultAddress())
                .defaultLatitude(dto.getDefaultLatitude())
                .defaultLongitude(dto.getDefaultLongitude())
                .build();
    }

    public CustomerResponseDto toDto(Customer entity) {
        if (entity == null) return null;

        return CustomerResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .defaultAddress(entity.getDefaultAddress())
                .defaultLatitude(entity.getDefaultLatitude())
                .defaultLongitude(entity.getDefaultLongitude())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntity(CustomerRequestDto dto, Customer entity) {
        if (dto == null || entity == null) return;
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }

}
