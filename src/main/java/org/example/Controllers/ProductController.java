package org.example.Controllers;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
    public boolean delete(@PathVariable(name = "id") int id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted
                ? ResponseEntity.noContent().build().hasBody()
                : ResponseEntity.notFound().build().hasBody();
    }

    @GetMapping(value = "/product/{name}")
    public List<Product> read(@PathVariable String name) {
        return productService.findByName(name);

    }

}
