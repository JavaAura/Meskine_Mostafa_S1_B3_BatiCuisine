package org.BatiCuisine.Repository.Interfaces;

import org.BatiCuisine.Model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    void addClient(Client client);

    Client getClientById(UUID id);

    List<Client> getAllClients();

    Optional<Client> getClientByName(String name);
}
