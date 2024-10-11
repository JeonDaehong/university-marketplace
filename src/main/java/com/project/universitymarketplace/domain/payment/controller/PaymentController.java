package com.project.universitymarketplace.domain.payment.controller;

import com.project.universitymarketplace.domain.payment.model.entity.Payment;
import com.project.universitymarketplace.domain.payment.model.entity.PaymentRequest;
import com.project.universitymarketplace.domain.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public record PaymentController(PaymentService paymentService) {

    @PostMapping
    public Payment createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.createPayment(paymentRequest.payment(), paymentRequest.productId());
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
