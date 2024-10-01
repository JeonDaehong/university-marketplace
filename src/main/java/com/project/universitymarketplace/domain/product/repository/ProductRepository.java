package com.project.universitymarketplace.domain.product.repository;

import com.project.universitymarketplace.domain.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
