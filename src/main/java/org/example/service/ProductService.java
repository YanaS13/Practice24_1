package org.example.service;

import org.example.model.Client;
import org.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProductService {
    private static final Map<Integer, Product> simpleDataBase = new HashMap<>();

    // Переменная для генерации ID клиента
    private Integer productId = 0;


    public void creatProduct(Product product) {
        product.setProductId(productId);
        simpleDataBase.put(productId, product);
        productId = productId + 1;
    }

    public Product getByID(int id) {
        return simpleDataBase.get(id);
    }
    public Boolean deleteProduct(int id){
        return simpleDataBase.remove(id) != null;
    }
    public List<Product> listOfProducts() {
        return new ArrayList<>(simpleDataBase.values());
    }
}
