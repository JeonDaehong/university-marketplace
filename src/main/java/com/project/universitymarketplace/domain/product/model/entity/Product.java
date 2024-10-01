package com.project.universitymarketplace.domain.product.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public record Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String name,
        String description,
        Double price,
        @CreatedDate @Column(updatable = false) LocalDateTime createdAt,
        @LastModifiedDate @Column(updatable = false) LocalDateTime updatedAt
) { }
