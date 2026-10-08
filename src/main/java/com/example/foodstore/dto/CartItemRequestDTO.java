package com.example.foodstore.dto;

import lombok.*;

@Getter
@Setter
public class CartItemRequestDTO {

    private Long cartId;

    private Long menuItemId;

    private Integer quantity;

}
