package org.example.controllers;

import lombok.Data;
import org.example.model.Client;
import org.example.service.BasketService;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Data
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private BasketService basketService;

    public ClientController(ClientService clientServices) {
        this.clientService = clientServices;
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody Client client) throws URISyntaxException {
        clientService.clientRegistration(client);
        return ResponseEntity
                .created(new URI("http://localhost:8080/clients/" + client.getId()))
                .build();
    }

    @GetMapping(value = "/clients/{name}")
    public Optional<Client> read(@PathVariable int id) {
        return clientService.getByID(id);


    }

}
