package com.example.foodstore.controller;

import com.example.foodstore.dto.DeliveryRequestDTO;
import com.example.foodstore.dto.DeliveryResponseDTO;
import com.example.foodstore.entity.Delivery;
import com.example.foodstore.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<DeliveryResponseDTO> getDeliveries() {
        return deliveryService.getDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryResponseDTO getDelivery(@PathVariable Long id) {
        return deliveryService.getDelivery(id);
    }

    @PostMapping
    public DeliveryResponseDTO createDelivery(@RequestBody DeliveryRequestDTO request) {
        return deliveryService.createDelivery(request);
    }

    @PutMapping("/{id}")
    public DeliveryResponseDTO updateDelivery(
            @PathVariable Long id,
            @RequestBody DeliveryRequestDTO request
    ) {
        return deliveryService.updateDelivery(id, request);
    }

}
