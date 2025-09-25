package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.DriverRequestDto;
import com.example.delivery.tracking.API.dto.response.DriverResponseDto;
import com.example.delivery.tracking.API.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Override
    public DriverResponseDto create(DriverRequestDto driverRequestDto) {
        return null;
    }

    @Override
    public List<DriverResponseDto> getAll() {
        return null;
    }

    @Override
    public DriverResponseDto getByID(Long aLong) {
        return null;
    }

    @Override
    public DriverResponseDto update(DriverRequestDto driverRequestDto, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
