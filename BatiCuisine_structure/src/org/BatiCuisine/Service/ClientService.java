package org.BatiCuisine.Service;

import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Repository.Interfaces.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) {
        clientRepository.addClient(client);
    }

    public Client getClientById(UUID id) {
        return clientRepository.getClientById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Optional<Client> getClientByName(String name){
        return clientRepository.getClientByName(name);
    }
}
