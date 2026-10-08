package com.example.foodstore.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemRequestDTO {

    private Long orderId;

    private Long menuItemId;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

}
