package org.example.Controllers;

import org.example.model.Client;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping(value = "/product")
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.creatProduct(product);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = productService.deleteProduct(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Product> read(@PathVariable(name = "id") int id){
        final Product product = productService.getByID(id);

        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping(value = "/product")
    public ResponseEntity<List<Product>> read() {
        final List<Product> products = productService.listOfProducts();

        return products != null &&  !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
