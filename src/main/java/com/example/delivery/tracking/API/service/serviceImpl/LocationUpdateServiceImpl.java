package com.example.delivery.tracking.API.service.serviceImpl;

import com.example.delivery.tracking.API.dto.request.LocationUpdateRequestDto;
import com.example.delivery.tracking.API.dto.response.LocationUpdateResponseDto;
import com.example.delivery.tracking.API.entity.Delivery;
import com.example.delivery.tracking.API.entity.Driver;
import com.example.delivery.tracking.API.entity.LocationUpdate;
import com.example.delivery.tracking.API.mapper.LocationUpdateMapper;
import com.example.delivery.tracking.API.repository.DeliveryRepository;
import com.example.delivery.tracking.API.repository.DriverRepository;
import com.example.delivery.tracking.API.repository.LocationUpdateRepository;
import com.example.delivery.tracking.API.service.LocationUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationUpdateServiceImpl implements LocationUpdateService {

    private final LocationUpdateMapper mapper;
    private final LocationUpdateRepository locationUpdateRepository;
    private final DriverRepository driverRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public LocationUpdateResponseDto create(LocationUpdateRequestDto dto) {
        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + dto.getDriverId()));

        Delivery delivery = null;
        if (dto.getDeliveryId() != null) {
            delivery = deliveryRepository.findById(dto.getDeliveryId())
                    .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + dto.getDeliveryId()));
        }

        LocationUpdate locationUpdate = mapper.toEntity(dto, driver, delivery);
        return mapper.toDto(locationUpdateRepository.save(locationUpdate));
    }

    @Override
    public List<LocationUpdateResponseDto> getAll() {
        return locationUpdateRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocationUpdateResponseDto getById(Long id) {
        LocationUpdate locationUpdate = locationUpdateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location update not found with id: " + id));
        return mapper.toDto(locationUpdate);
    }

    @Override
    public LocationUpdateResponseDto update(Long id, LocationUpdateRequestDto dto) {
        LocationUpdate existing = locationUpdateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location update not found with id: " + id));

        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + dto.getDriverId()));

        Delivery delivery = null;
        if (dto.getDeliveryId() != null) {
            delivery = deliveryRepository.findById(dto.getDeliveryId())
                    .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + dto.getDeliveryId()));
        }

        mapper.updateEntity(dto, existing, driver, delivery);

        LocationUpdate updated = locationUpdateRepository.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        LocationUpdate locationUpdate = locationUpdateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location update not found with id: " + id));
        locationUpdateRepository.delete(locationUpdate);
    }

    @Override
    public List<LocationUpdateResponseDto> getByDriver(Long driverId) {
        return locationUpdateRepository.findByDriverId(driverId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationUpdateResponseDto> getByDelivery(Long deliveryId) {
        return locationUpdateRepository.findByDeliveryId(deliveryId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
