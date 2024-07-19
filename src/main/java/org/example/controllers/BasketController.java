package org.example.Controllers;

import org.example.model.Client;
import org.example.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {
    private BasketService basketService;
    public BasketController(BasketService basketService){
        this.basketService = basketService;
    }
    @PostMapping(value = "/basket/{basketID}/addProduct/{productID}")
    public ResponseEntity<?> add(@PathVariable Integer basketID,@PathVariable Integer productID) {
        basketService.addToBasket(basketID,productID);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/basket/{basketID}/delete/{productID}")
    public ResponseEntity<?> delete(@PathVariable Integer basketID,@PathVariable Integer productID) {
        final boolean deleted = basketService.deleteFromBasket(basketID,productID);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
