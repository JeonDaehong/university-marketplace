package com.project.universitymarketplace.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
        String name,
        String description,
        Double price
) { }
