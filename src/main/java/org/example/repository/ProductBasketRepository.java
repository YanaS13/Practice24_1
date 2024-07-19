package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.model.Basket;
import org.example.model.Product;
import org.example.model.ProductBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductBasketRepository extends JpaRepository<ProductBasket, Integer> {
    Optional<ProductBasket> searchBasketProduct(Basket basket, Product product);

    @Modifying
    @Transactional
    void deleteAllByBinId(Basket basket, Product product);

    @Modifying
    @Transactional
    void deleteByBinIdAndProduct(Basket basket, Product productId);
}