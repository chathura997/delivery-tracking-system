package com.example.delivery.tracking.API.kafka;

import com.example.delivery.tracking.API.dto.event.DeliveryStatusEvent;
import com.example.delivery.tracking.API.dto.event.LocationUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "driver-location-updates", groupId = "delivery-tracking-group")
    public void consumeLocationUpdate(LocationUpdateEvent event) {
        log.info("Received location update: Driver {} at ({}, {})",
                event.getDriverId(), event.getLatitude(), event.getLongitude());

        // Later: Update Redis cache, broadcast via WebSocket
    }

    @KafkaListener(topics = "delivery-status-updates", groupId = "delivery-tracking-group")
    public void consumeDeliveryStatus(DeliveryStatusEvent event) {
        log.info("Received delivery status: Delivery {} changed from {} to {}",
                event.getDeliveryId(), event.getOldStatus(), event.getNewStatus());

        // Later: Send notifications, update cache
    }
}
