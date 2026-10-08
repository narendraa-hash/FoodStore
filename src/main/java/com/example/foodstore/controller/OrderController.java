package com.example.foodstore.controller;

import com.example.foodstore.dto.OrderRequestDTO;
import com.example.foodstore.dto.OrderResponseDTO;
import com.example.foodstore.entity.Order;
import com.example.foodstore.repository.OrderRepository;
import com.example.foodstore.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDTO> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO request) {
        return orderService.createOrder(request);
    }

}
