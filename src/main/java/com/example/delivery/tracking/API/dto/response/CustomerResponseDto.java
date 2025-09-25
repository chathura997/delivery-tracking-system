package com.example.delivery.tracking.API.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String defaultAddress;
    private BigDecimal defaultLatitude;
    private BigDecimal defaultLongitude;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
