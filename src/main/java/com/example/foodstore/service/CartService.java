package com.example.foodstore.service;

import com.example.foodstore.dto.CartRequestDTO;
import com.example.foodstore.dto.CartResponseDTO;
import com.example.foodstore.entity.Cart;
import com.example.foodstore.entity.User;
import com.example.foodstore.repository.CartRepository;
import com.example.foodstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            UserRepository userRepository
    ) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public CartResponseDTO createCart(CartRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart();

        cart.setUser(user);

        Cart savedCart = cartRepository.save(cart);

        return convertToResponse(savedCart);
    }

    public List<CartResponseDTO> getCarts() {

        return cartRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public CartResponseDTO getCart(Long id) {

        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return convertToResponse(cart);
    }

    public CartResponseDTO getCartByUser(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return convertToResponse(cart);
    }

    private CartResponseDTO convertToResponse(Cart cart) {

        return new CartResponseDTO(
                cart.getId(),
                cart.getUser().getId(),
                cart.getCreatedAt(),
                cart.getUpdatedAt()
        );
    }

}
