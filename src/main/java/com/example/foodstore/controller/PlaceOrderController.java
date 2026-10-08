package com.example.foodstore.controller;

import com.example.foodstore.dto.PlaceOrderRequestDTO;
import com.example.foodstore.dto.PlaceOrderResponseDTO;
import com.example.foodstore.service.PlaceOrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class PlaceOrderController {

    private final PlaceOrderService placeOrderService;

    public PlaceOrderController(PlaceOrderService placeOrderService) {
        this.placeOrderService = placeOrderService;
    }

    @PostMapping("/place")
    public PlaceOrderResponseDTO placeOrder(
            @RequestBody PlaceOrderRequestDTO request
    ) {
        return placeOrderService.placeOrder(request);
    }

}
