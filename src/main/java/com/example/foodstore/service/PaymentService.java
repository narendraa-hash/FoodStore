package com.example.foodstore.service;

import com.example.foodstore.dto.PaymentRequestDTO;
import com.example.foodstore.dto.PaymentResponseDTO;
import com.example.foodstore.entity.Order;
import com.example.foodstore.entity.Payment;
import com.example.foodstore.repository.OrderRepository;
import com.example.foodstore.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus(request.getStatus());
        payment.setTransactionId(request.getTransactionId());

        Payment savedPayment = paymentRepository.save(payment);

        return convertToResponse(savedPayment);
    }

    public List<PaymentResponseDTO> getPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public PaymentResponseDTO getPayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return convertToResponse(payment);
    }

    private PaymentResponseDTO convertToResponse(Payment payment) {

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getOrder().getId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getTransactionId(),
                payment.getCreatedAt()
        );
    }

}
