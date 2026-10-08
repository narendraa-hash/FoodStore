package com.example.foodstore.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO {

    private Long id;

    private Long cartId;

    private Long menuItemId;

    private String menuItemName;

    private BigDecimal menuItemPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

}
