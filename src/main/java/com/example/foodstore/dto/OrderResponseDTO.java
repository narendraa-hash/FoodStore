package com.example.foodstore.dto;

import com.example.foodstore.entity.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;

    private Long userId;

    private Long addressId;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private LocalDateTime createdAt;

}
