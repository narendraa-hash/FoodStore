package com.example.foodstore.dto;

import lombok.*;

@Getter
@Setter
public class PlaceOrderRequestDTO {

    private Long userId;

    private Long addressId;

}
