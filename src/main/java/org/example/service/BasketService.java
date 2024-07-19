package org.example.service;

import org.example.model.Sold;
import org.example.repository.BasketRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;
    private ProductRepository productRepository;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
    }

    public Integer creatBasket() {
        return basketRepository.creatBasket();
    }

    public int addToBasket(Integer basketId, Integer productID) {
        return basketRepository.creatIn(basketId, productID);
    }

    public Boolean deleteBasket(Integer productID, Integer basketId) {
        return productRepository.deleteById(productID, basketId);
    }
    @Transactional
    public void pay(int binId) {
        List<Sold> sold = basketRepository.findAll(binId);
        for (Sold solds : sold) {
            productRepository.sell(solds);
        }
        basketRepository.purchase(binId);
    }
}
