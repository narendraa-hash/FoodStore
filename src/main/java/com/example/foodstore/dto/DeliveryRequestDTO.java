package com.example.foodstore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryRequestDTO {

    private Long orderId;

    private String status;

    private String deliveryPartner;

    private String partnerPhone;

    private LocalDateTime estimatedDeliveryTime;

    private LocalDateTime deliveredAt;

}
