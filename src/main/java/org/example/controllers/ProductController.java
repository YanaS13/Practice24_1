package org.example.controllers;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<?> create(@RequestBody Product product) throws URISyntaxException {
        productService.creatProduct(product);
        return ResponseEntity
                .created(new URI("http://localhost:8080/product/" + product.getId()))
                .build();
    }

    @DeleteMapping(value = "/product/delete")
    public ResponseEntity<?> delete(@PathVariable int id) {
        productService.deleteProduct(id);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @GetMapping(value = "/product/{name}")
    public Optional<Product> read(@PathVariable int id) {
        return productService.findByName(id);

    }

}
