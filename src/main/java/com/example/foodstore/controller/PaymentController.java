package com.example.foodstore.controller;

import com.example.foodstore.dto.PaymentRequestDTO;
import com.example.foodstore.dto.PaymentResponseDTO;
import com.example.foodstore.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentResponseDTO> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("/{id}")
    public PaymentResponseDTO getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @PostMapping
    public PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.createPayment(request);
    }

}
