package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.DriverRequestDto;
import com.example.delivery.tracking.API.dto.response.DriverResponseDto;
import com.example.delivery.tracking.API.entity.Driver;
import com.example.delivery.tracking.API.mapper.DriverMapper;
import com.example.delivery.tracking.API.repository.DriverRepository;
import com.example.delivery.tracking.API.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverMapper driverMapper;
    private final DriverRepository driverRepository;
    @Override
    public DriverResponseDto create(DriverRequestDto driverRequestDto) {
        Driver driver = driverMapper.toEntity(driverRequestDto);
        try {
            Driver savedDriver = driverRepository.save(driver);
            return driverMapper.toDto(savedDriver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create driver", e);
        }
    }

    @Override
    public List<DriverResponseDto> getAll() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream()
                .map(driverMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DriverResponseDto getByID(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Driver not found this id: "+ id));
        return driverMapper.toDto(driver);
    }

    @Override
    public DriverResponseDto update(DriverRequestDto driverRequestDto, Long id) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));

        driverMapper.updateEntity(driverRequestDto, existingDriver);

        Driver updatedDriver = driverRepository.save(existingDriver);
        return driverMapper.toDto(updatedDriver);
    }


    @Override
    public void delete(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        driverRepository.delete(driver);
    }

}
