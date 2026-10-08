package com.example.foodstore.service;

import com.example.foodstore.dto.OrderRequestDTO;
import com.example.foodstore.dto.OrderResponseDTO;
import com.example.foodstore.entity.Address;
import com.example.foodstore.entity.Order;
import com.example.foodstore.entity.User;
import com.example.foodstore.repository.AddressRepository;
import com.example.foodstore.repository.OrderRepository;
import com.example.foodstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository,
            AddressRepository addressRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Order order = new Order();
        order.setAddress(address);
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus(request.getStatus());

        Order savedOrder = orderRepository.save(order);

        return convertToResponse(savedOrder);
    }

    public List<OrderResponseDTO> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public OrderResponseDTO getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return convertToResponse(order);
    }

    private OrderResponseDTO convertToResponse(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getAddress().getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

}
