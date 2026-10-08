package com.example.foodstore.service;

import com.example.foodstore.dto.OrderItemResponseDTO;
import com.example.foodstore.dto.PlaceOrderRequestDTO;
import com.example.foodstore.dto.PlaceOrderResponseDTO;
import com.example.foodstore.entity.Address;
import com.example.foodstore.entity.Cart;
import com.example.foodstore.entity.CartItem;
import com.example.foodstore.entity.Order;
import com.example.foodstore.entity.OrderItem;
import com.example.foodstore.entity.OrderStatus;
import com.example.foodstore.entity.User;
import com.example.foodstore.repository.AddressRepository;
import com.example.foodstore.repository.CartItemRepository;
import com.example.foodstore.repository.CartRepository;
import com.example.foodstore.repository.OrderItemRepository;
import com.example.foodstore.repository.OrderRepository;
import com.example.foodstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceOrderService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public PlaceOrderService(
            UserRepository userRepository,
            AddressRepository addressRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public PlaceOrderResponseDTO placeOrder(PlaceOrderRequestDTO request) {

        // 1. Find user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Find address
        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // 3. Find user's cart
        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // 4. Get cart items
        List<CartItem> cartItems =
                cartItemRepository.findByCartId(cart.getId());

        // 5. Make sure cart is not empty
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // 6. Calculate total
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {

            BigDecimal itemTotal = cartItem.getMenuItem()
                    .getPrice()
                    .multiply(
                            BigDecimal.valueOf(cartItem.getQuantity())
                    );

            totalAmount = totalAmount.add(itemTotal);
        }

        // 7. Create Order
        Order order = new Order();

        order.setUser(user);
        order.setAddress(address);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PLACED);

        Order savedOrder = orderRepository.save(order);

        // 8. Create OrderItems
        List<OrderItemResponseDTO> responseItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            BigDecimal unitPrice =
                    cartItem.getMenuItem().getPrice();

            BigDecimal itemTotal = unitPrice.multiply(
                    BigDecimal.valueOf(cartItem.getQuantity())
            );

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setMenuItem(cartItem.getMenuItem());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(unitPrice);
            orderItem.setTotalPrice(itemTotal);

            OrderItem savedOrderItem =
                    orderItemRepository.save(orderItem);

            responseItems.add(
                    new OrderItemResponseDTO(
                            savedOrderItem.getId(),
                            savedOrder.getId(),
                            savedOrderItem.getMenuItem().getId(),
                            savedOrderItem.getQuantity(),
                            savedOrderItem.getUnitPrice(),
                            savedOrderItem.getTotalPrice()
                    )
            );
        }

        // 9. Clear cart
        cartItemRepository.deleteByCartId(cart.getId());

        // 10. Return response
        return new PlaceOrderResponseDTO(
                savedOrder.getId(),
                user.getId(),
                address.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getCreatedAt(),
                responseItems
        );
    }

}
