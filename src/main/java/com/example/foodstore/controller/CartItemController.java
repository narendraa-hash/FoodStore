package com.example.foodstore.controller;

import com.example.foodstore.dto.CartItemRequestDTO;
import com.example.foodstore.dto.CartItemResponseDTO;
import com.example.foodstore.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public List<CartItemResponseDTO> getCartItems() {
        return cartItemService.getCartItems();
    }

    @GetMapping("/{id}")
    public CartItemResponseDTO getCartItem(
            @PathVariable Long id
    ) {
        return cartItemService.getCartItem(id);
    }

    @PostMapping
    public CartItemResponseDTO addCartItem(
            @RequestBody CartItemRequestDTO request
    ) {
        return cartItemService.addCartItem(request);
    }

    @PutMapping("/{id}")
    public CartItemResponseDTO updateCartItem(
            @PathVariable Long id,
            @RequestBody CartItemRequestDTO request
    ) {
        return cartItemService.updateCartItem(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(
            @PathVariable Long id
    ) {
        cartItemService.deleteCartItem(id);
    }

}
