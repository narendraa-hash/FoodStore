package com.example.foodstore.repository;

import com.example.foodstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /* This is Cart entity */
    Optional<Cart> findByUserId(Long userId);

}
