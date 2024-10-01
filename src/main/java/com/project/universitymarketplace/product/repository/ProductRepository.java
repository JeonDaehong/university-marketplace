package com.project.universitymarketplace.product.repository;

import com.project.universitymarketplace.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
