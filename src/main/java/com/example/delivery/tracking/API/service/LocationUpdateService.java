package com.example.delivery.tracking.API.service;

import com.example.delivery.tracking.API.dto.request.LocationUpdateRequestDto;
import com.example.delivery.tracking.API.dto.response.LocationUpdateResponseDto;

public interface LocationUpdateService extends GenericCrudService<LocationUpdateResponseDto, LocationUpdateRequestDto,Long>{
}
