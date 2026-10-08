package com.example.foodstore.service;

import com.example.foodstore.dto.CartItemRequestDTO;
import com.example.foodstore.dto.CartItemResponseDTO;
import com.example.foodstore.entity.Cart;
import com.example.foodstore.entity.CartItem;
import com.example.foodstore.entity.MenuItem;
import com.example.foodstore.repository.CartItemRepository;
import com.example.foodstore.repository.CartRepository;
import com.example.foodstore.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final MenuItemRepository menuItemRepository;

    public CartItemService(
            CartItemRepository cartItemRepository,
            CartRepository cartRepository,
            MenuItemRepository menuItemRepository
    ) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public CartItemResponseDTO addCartItem(CartItemRequestDTO request) {

        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(request.getQuantity());

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        return convertToResponse(savedCartItem);
    }

    public List<CartItemResponseDTO> getCartItems() {

        return cartItemRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public CartItemResponseDTO getCartItem(Long id) {

        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        return convertToResponse(cartItem);
    }

    public CartItemResponseDTO updateCartItem(
            Long id,
            CartItemRequestDTO request
    ) {

        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (request.getQuantity() != null) {
            cartItem.setQuantity(request.getQuantity());
        }

        CartItem updatedCartItem = cartItemRepository.save(cartItem);

        return convertToResponse(updatedCartItem);
    }

    public void deleteCartItem(Long id) {

        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItemRepository.delete(cartItem);
    }

    private CartItemResponseDTO convertToResponse(CartItem cartItem) {

        MenuItem menuItem = cartItem.getMenuItem();

        BigDecimal totalPrice = menuItem.getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return new CartItemResponseDTO(
                cartItem.getId(),
                cartItem.getCart().getId(),
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getPrice(),
                cartItem.getQuantity(),
                totalPrice
        );
    }

}
