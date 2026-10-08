package com.example.foodstore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Long id;

    private Long orderId;

    private BigDecimal amount;

    private String method;

    private String status;

    private String transactionId;

    private LocalDateTime createdAt;

}
