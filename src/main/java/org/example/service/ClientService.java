package org.example.service;

import org.example.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {
    private static final Map<Integer, Client> simpleDataBase = new HashMap<>();


    private Integer userID = 0;

    private BasketService basketService = new BasketService();

    public void clientRegistration(Client client) {
        client.setId(userID);
        client.setBasketID(basketService.creatBasket());
        simpleDataBase.put(userID, client);
        userID = userID + 1;
    }

    public Client getByID(int id) {
        return simpleDataBase.get(id);
    }

}