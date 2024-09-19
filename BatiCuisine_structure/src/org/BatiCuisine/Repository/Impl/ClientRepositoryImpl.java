package org.BatiCuisine.Repository.Impl;

import org.BatiCuisine.Dao.Interfaces.ClientDao;
import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Repository.Interfaces.ClientRepository;

import java.util.List;
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
    public void updateClient(Client client) {
        clientDao.update(client);
    }

    @Override
    public boolean removeClient(UUID id) {
        return clientDao.delete(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAll();
    }
}
