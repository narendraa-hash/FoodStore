package com.example.foodstore.controller;

import com.example.foodstore.dto.OrderItemRequestDTO;
import com.example.foodstore.dto.OrderItemResponseDTO;
import com.example.foodstore.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItemResponseDTO> getOrderItems() {
        return orderItemService.getOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemResponseDTO getOrderItem(@PathVariable Long id) {
        return orderItemService.getOrderItem(id);
    }

    @PostMapping
    public OrderItemResponseDTO createOrderItem(
            @RequestBody OrderItemRequestDTO request
    ) {
        return orderItemService.createOrderItem(request);
    }

}
