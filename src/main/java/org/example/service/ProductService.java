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

    public long creatProduct(Product product) {
        return productRepository.creatProduct(product);
    }

    public Boolean deleteProduct(int id) {
        return this.productRepository.deleteById(id);
    }

    public Optional<Product> findById(Integer id) {
        return this.productRepository.findById(id);
    }
}
