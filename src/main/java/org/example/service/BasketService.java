package org.example.service;

import org.example.model.Client;
import org.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class BasketService {
    private static final Map<Integer, ArrayList<Product>> simpleDataBase = new HashMap<>();
    private Integer basketID = 0;
    private ProductService productService = new ProductService();
    public Integer creatBasket() {
        Integer basketIDNew = basketID ;
        simpleDataBase.put(basketIDNew,new ArrayList<Product>());
        basketID = basketID + 1;
        return basketIDNew;
    }
    public void addToBasket(Integer basketId,Integer productID){
        ArrayList<Product> listOfProducts = new ArrayList<>();
        listOfProducts = simpleDataBase.get(basketId);
        listOfProducts.add(productService.getByID(productID));
        simpleDataBase.put(basketId,listOfProducts);
    }
    public Boolean deleteFromBasket(Integer basketId,Integer productID){
        ArrayList<Product> listOfProducts = new ArrayList<>();
        Boolean done = Boolean.FALSE;
        listOfProducts = simpleDataBase.get(basketId);
        for (Product element : listOfProducts){
            if (element.getProductId().equals(productID)){
                listOfProducts.remove(element);
                done = Boolean.TRUE;
            }
        }
        simpleDataBase.put(basketId,listOfProducts);
        return done;
    }
}
