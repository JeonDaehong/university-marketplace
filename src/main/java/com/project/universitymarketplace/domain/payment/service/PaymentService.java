package com.project.universitymarketplace.domain.payment.service;

import com.project.universitymarketplace.common.exception.PaymentNotFoundException;
import com.project.universitymarketplace.common.exception.ProductNotFoundException;
import com.project.universitymarketplace.domain.payment.model.entity.Payment;
import com.project.universitymarketplace.domain.payment.repository.PaymentRepository;
import com.project.universitymarketplace.domain.product.model.entity.Product;
import com.project.universitymarketplace.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record PaymentService (PaymentRepository paymentRepository, ProductRepository productRepository){

    public Payment createPayment(Payment payment, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        // 재고 수량 확인 및 감소
        if (product.stockQuantity() <= 0) {
            throw new IllegalArgumentException("제품의 재고가 부족합니다.");
        }

        // 재고 수량 감소 (레코드는 불변이므로 새로운 인스턴스 생성)
        Product updatedProduct = new Product(
                product.id(),
                product.name(),
                product.price(),
                product.stockQuantity() - 1
        );
        productRepository.save(updatedProduct);

        // 결제 금액 설정 (제품 가격)
        Payment newPayment = new Payment(
                null,
                payment.orderId(),
                product.price(),
                payment.currency(),
                payment.method(),
                null,
                product
        );

        return paymentRepository.save(newPayment);
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
