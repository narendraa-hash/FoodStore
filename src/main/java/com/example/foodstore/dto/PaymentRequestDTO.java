package com.example.foodstore.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequestDTO {

    private Long orderId;

    private BigDecimal amount;

    private String method;

    private String status;

    private String transactionId;

}
