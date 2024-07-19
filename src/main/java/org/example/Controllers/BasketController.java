package org.example.Controllers;

import lombok.Data;
import org.example.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Data
@RestController
public class BasketController {
    @Autowired
    private BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping(value = "/basket")l
    public ResponseEntity<?> add(Integer basketID, @RequestBody Integer productID) throws URISyntaxException {
        basketService.addToBasket(basketID, productID);
        return ResponseEntity.created(new URI("http://localhost:8080/basket/" + productID))
                .build();
    }

    @DeleteMapping(value = "basketDelete")
    public ResponseEntity<Void> deleteBasket(@PathVariable Integer basketID, @PathVariable Integer productID) {
        final boolean deleted = basketService.deleteFromBasket(basketID, productID);

        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
