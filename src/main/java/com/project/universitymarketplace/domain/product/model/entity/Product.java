package com.project.universitymarketplace.domain.product.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public record Product (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String name,
        String description,
        Double price,
        @NotNull @PositiveOrZero int stockQuantity,
        @CreatedDate @Column(updatable = false) LocalDateTime createdAt,
        @LastModifiedDate @Column(updatable = false) LocalDateTime updatedAt
) {
    public Product(Long id, String name, String description, Double price) {
        this(id, name, description, price,0, null, null);
    }
    public Product(Long id, String name, Double price, int stockQuantity) {
        this(id, name,null, price, stockQuantity, null, null);
    }
}
