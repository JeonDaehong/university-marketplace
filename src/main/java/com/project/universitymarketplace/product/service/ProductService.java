package com.project.universitymarketplace.product.service;

import com.project.universitymarketplace.product.entity.Product;
import com.project.universitymarketplace.product.exception.ProductNotFoundException;
import com.project.universitymarketplace.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProductService(ProductRepository productRepository) {

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        Product updatedProduct = new Product(id, product.name(), product.description(), product.price());
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
