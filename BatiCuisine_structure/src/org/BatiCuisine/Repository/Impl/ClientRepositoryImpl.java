package org.BatiCuisine.Repository.Impl;

import org.BatiCuisine.Dao.Interfaces.ClientDao;
import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Repository.Interfaces.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {

    private final ClientDao clientDao;

    public ClientRepositoryImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void addClient(Client client) {
        clientDao.create(client);
    }

    @Override
    public Client getClientById(UUID id) {
        return clientDao.read(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAll();
    }

    @Override
    public Optional<Client> getClientByName(String name) {
        return clientDao.getAll()
                .stream()
                .filter(client -> client.getName().equals(name))
                .findFirst();
    }
}
