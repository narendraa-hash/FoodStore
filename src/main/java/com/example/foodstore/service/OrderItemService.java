package com.example.foodstore.service;

import com.example.foodstore.dto.OrderItemRequestDTO;
import com.example.foodstore.dto.OrderItemResponseDTO;
import com.example.foodstore.entity.MenuItem;
import com.example.foodstore.entity.Order;
import com.example.foodstore.entity.OrderItem;
import com.example.foodstore.repository.MenuItemRepository;
import com.example.foodstore.repository.OrderItemRepository;
import com.example.foodstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderItemService(
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository,
            MenuItemRepository menuItemRepository
    ) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public OrderItemResponseDTO createOrderItem(OrderItemRequestDTO request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setUnitPrice(request.getUnitPrice());
        orderItem.setTotalPrice(request.getTotalPrice());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return convertToResponse(savedOrderItem);
    }

    public List<OrderItemResponseDTO> getOrderItems() {

        return orderItemRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public OrderItemResponseDTO getOrderItem(Long id) {

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        return convertToResponse(orderItem);
    }

    private OrderItemResponseDTO convertToResponse(OrderItem orderItem) {

        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getOrder().getId(),
                orderItem.getMenuItem().getId(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getTotalPrice()
        );
    }

}
