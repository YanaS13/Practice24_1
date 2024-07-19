package org.example.service;

import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    private ClientService(ClientRepository сlientRepository) {
        this.clientRepository = сlientRepository;
    }

    public void clientRegistration(Client client) {
        clientRepository.save(client);
    }

    public Optional<Client> getByID(int id) {
        return this.clientRepository.findById(id);
    }

}