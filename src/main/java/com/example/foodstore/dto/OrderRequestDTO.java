package com.example.foodstore.dto;

import com.example.foodstore.entity.OrderStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequestDTO {

    private Long userId;

    private Long addressId;

    private BigDecimal totalAmount;

    private OrderStatus status;

}
