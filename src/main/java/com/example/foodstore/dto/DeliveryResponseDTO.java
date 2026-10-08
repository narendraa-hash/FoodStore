package com.example.foodstore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponseDTO {

    private Long id;

    private Long orderId;

    private String status;

    private String deliveryPartner;

    private String PartnerPhone;

    private LocalDateTime estimatedDeliveryTime;

    private LocalDateTime deliveredAt;

    private LocalDateTime createdAt;

}
