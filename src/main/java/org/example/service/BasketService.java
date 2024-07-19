package org.example.service;

import org.example.model.Basket;
import org.example.model.Product;
import org.example.model.ProductBasket;
import org.example.repository.BasketRepository;
import org.example.repository.ProductBasketRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {

    private BasketRepository basketRepository;
    private ProductBasketRepository productBasketRepository;
    @Autowired
    public BasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productBasketRepository = productBasketRepository;
    }

    public Basket creatBasket() {
        Basket basket = new Basket();
        basket.setPromocode("promo");
        return basketRepository.save(basket);
    }
    public Optional<Basket> findById(int id){
        return basketRepository.findById(id);
    }

    public void addToBasket(ProductBasket productBasket) {
         productBasketRepository.save(productBasket);
    }

    public void deleteBasket(Basket basket, Product product) {
        productBasketRepository.deleteAllByBinId(basket,product);
    }
}
