package com.project.universitymarketplace.domain.payment.model.entity;

import com.project.universitymarketplace.domain.product.model.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public record Payment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        @NotBlank String orderId,
        @NotNull @Positive Double amount,
        @NotBlank String currency,
        @NotBlank String method,
        @CreatedDate @Column(updatable = false) LocalDateTime createdAt,
        @ManyToOne @JoinColumn(name = "product_id")
        @NotNull Product product
) { }
