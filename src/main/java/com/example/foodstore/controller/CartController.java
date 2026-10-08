package com.example.foodstore.controller;

import com.example.foodstore.dto.CartRequestDTO;
import com.example.foodstore.dto.CartResponseDTO;
import com.example.foodstore.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartResponseDTO> getCarts() {
        return cartService.getCarts();
    }

    @GetMapping("/{id}")
    public CartResponseDTO getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @GetMapping("/user/{userId}")
    public CartResponseDTO getCartByUser(
            @PathVariable Long userId
    ) {
        return cartService.getCartByUser(userId);
    }

    @PostMapping
    public CartResponseDTO createCart(
            @RequestBody CartRequestDTO request
    ) {
        return cartService.createCart(request);
    }

}