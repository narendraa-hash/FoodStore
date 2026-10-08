package com.example.foodstore.service;

import com.example.foodstore.dto.DeliveryRequestDTO;
import com.example.foodstore.dto.DeliveryResponseDTO;
import com.example.foodstore.entity.Delivery;
import com.example.foodstore.entity.Order;
import com.example.foodstore.repository.DeliveryRepository;
import com.example.foodstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    public DeliveryService(
            DeliveryRepository deliveryRepository,
            OrderRepository orderRepository
    ) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
    }

    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Delivery delivery = new Delivery();

        delivery.setOrder(order);
        delivery.setStatus(request.getStatus());
        delivery.setDeliveryPartner(request.getDeliveryPartner());
        delivery.setPartnerPhone(request.getPartnerPhone());
        delivery.setEstimatedDeliveryTime(request.getEstimatedDeliveryTime());
        delivery.setDeliveredAt(request.getDeliveredAt());

        Delivery savedDelivery = deliveryRepository.save(delivery);

        return convertToResponse(savedDelivery);
    }

    public List<DeliveryResponseDTO> getDeliveries() {

        return deliveryRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public DeliveryResponseDTO getDelivery(Long id) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        return convertToResponse(delivery);
    }

    public DeliveryResponseDTO updateDelivery(
            Long id,
            DeliveryRequestDTO request
    ) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        if (request.getOrderId() != null) {

            Order order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            delivery.setOrder(order);
        }

        if (request.getStatus() != null) {
            delivery.setStatus(request.getStatus());
        }

        if (request.getDeliveryPartner() != null) {
            delivery.setDeliveryPartner(request.getDeliveryPartner());
        }

        if (request.getPartnerPhone() != null) {
            delivery.setPartnerPhone(request.getPartnerPhone());
        }

        if (request.getEstimatedDeliveryTime() != null) {
            delivery.setEstimatedDeliveryTime(
                    request.getEstimatedDeliveryTime()
            );
        }

        if (request.getDeliveredAt() != null) {
            delivery.setDeliveredAt(request.getDeliveredAt());
        }

        Delivery updatedDelivery = deliveryRepository.save(delivery);

        return convertToResponse(updatedDelivery);
    }

    private DeliveryResponseDTO convertToResponse(Delivery delivery) {

        return new DeliveryResponseDTO(
                delivery.getId(),
                delivery.getOrder().getId(),
                delivery.getStatus(),
                delivery.getDeliveryPartner(),
                delivery.getPartnerPhone(),
                delivery.getEstimatedDeliveryTime(),
                delivery.getDeliveredAt(),
                delivery.getCreatedAt()
        );
    }

}
