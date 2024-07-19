package org.example.controllers;

import lombok.Data;
import org.example.model.Basket;
import org.example.model.Product;
import org.example.model.ProductBasket;
import org.example.service.BasketService;
import org.example.service.ClientService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Data
@RestController
public class BasketController {
    @Autowired
    private BasketService basketService;
    private ClientService clientService;
    private ProductService productService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PutMapping(value = "add/{basketId}/{productId}")
    public ResponseEntity<?> add(@PathVariable Integer basketID, @PathVariable Integer productID) throws URISyntaxException {
        Optional<Basket> basket = basketService.findById(basketID);
        Optional<Product> product = productService.findByName(productID);

        ProductBasket productBasket = new ProductBasket();
        productBasket.setBasket(basket.get());
        productBasket.setProduct(product.get());
        basketService.addToBasket(productBasket);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(path = "delit/{basketId}/{productId}")
    public ResponseEntity<Void> deleteBasket(@PathVariable int productId, @PathVariable int basketId) {
        Optional<Basket> basket = basketService.findById(basketId);
        Optional<Product> product = productService.findByName(productId);

        basketService.deleteBasket(basket.get(), product.get());
        return ResponseEntity.ok().build();
    }

    @PutMapping("pay/{ProductId}{basketId}/")
    public void payment(@PathVariable int basketId, @PathVariable int productId) {
        Optional<Basket> basket = basketService.findById(basketId);
        Optional<Product> product = productService.findByName(productId);
        basketService.deleteBasket(basket.get(), product.get());
    }
}

