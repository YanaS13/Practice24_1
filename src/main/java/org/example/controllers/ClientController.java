package org.example.Controllers;

import org.example.model.Client;
import org.example.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientServices) {
        this.clientService = clientServices;
    }
    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody Client client) {
        clientService.clientRegistration(client);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id){
        final Client client = clientService.getByID(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
