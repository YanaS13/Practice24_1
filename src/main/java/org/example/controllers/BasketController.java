package org.example.controllers;

import lombok.Data;
import org.example.service.BasketService;
import org.example.service.ClientService;
import org.example.service.ProductService;
import org.example.model.Basket;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Data
@RestController
public class BasketController {
    @Autowired
    private BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PutMapping(value = "/basketId")
    public ResponseEntity<?> add(@PathVariable Integer basketID, @PathVariable Integer productID) throws URISyntaxException {
        basketService.addToBasket(basketID,productID);
        return ResponseEntity
                .created(new URI("http://localhost:8080/basket/" + productID))
                .build();
    }


    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteBasket(@PathVariable int productId, @PathVariable int basketId) {
        boolean delete = basketService.deleteBasket(productId, basketId);
        return delete
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("pay/")
    public void payment(@PathVariable int bin) {
        basketService.pay(bin);
    }
}

