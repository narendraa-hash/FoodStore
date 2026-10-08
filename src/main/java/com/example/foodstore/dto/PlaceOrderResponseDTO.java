package com.example.foodstore.dto;

import com.example.foodstore.entity.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderResponseDTO {

    private Long orderId;

    private Long userId;

    private Long addressId;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private LocalDateTime createdId;

    private List<OrderItemResponseDTO> items;

}
