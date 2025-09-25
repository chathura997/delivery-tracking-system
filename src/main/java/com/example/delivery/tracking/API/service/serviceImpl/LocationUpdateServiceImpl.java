package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.LocationUpdateRequestDto;
import com.example.delivery.tracking.API.dto.response.LocationUpdateResponseDto;
import com.example.delivery.tracking.API.service.LocationUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationUpdateServiceImpl implements LocationUpdateService {
    @Override
    public LocationUpdateResponseDto create(LocationUpdateRequestDto locationUpdateRequestDto) {
        return null;
    }

    @Override
    public List<LocationUpdateResponseDto> getAll() {
        return null;
    }

    @Override
    public LocationUpdateResponseDto getByID(Long aLong) {
        return null;
    }

    @Override
    public LocationUpdateResponseDto update(LocationUpdateRequestDto locationUpdateRequestDto, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
