package org.BatiCuisine.Repository.Interfaces;

import org.BatiCuisine.Model.Client;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {
    void addClient(Client client);

    Client getClientById(UUID id);

    void updateClient(Client client);

    boolean removeClient(UUID id);

    List<Client> getAllClients();

    Client getClientByName(String name);
}
