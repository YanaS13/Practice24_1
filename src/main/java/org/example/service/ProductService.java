package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    private ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product creatProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) { this.productRepository.deleteById(id);
    }

    public Optional<Product> findByName(Integer id) {
        return this.productRepository.findById(id);
    }
    public void sell(int quantity, int productId) {
        this.productRepository.sell(quantity, productId);
    }
}

