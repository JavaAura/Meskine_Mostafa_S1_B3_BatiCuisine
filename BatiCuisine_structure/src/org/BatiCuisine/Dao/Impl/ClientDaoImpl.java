package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.ClientDao;
import org.BatiCuisine.Database.DbConnection;
import org.BatiCuisine.Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientDaoImpl implements ClientDao {
    private final Connection connection = DbConnection.getInstance();

    @Override
    public void create(Client client) {
        String query = "INSERT INTO clients (clientID, name, address, phone, isProfessional) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setObject(1, client.getClientID());  // UUID
            ps.setString(2, client.getName());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getPhone());
            ps.setBoolean(5, client.isProfessional());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client created successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error creating client: " + e.getMessage());
        }
    }

    @Override
    public Client read(UUID id) {
        String query = "SELECT * FROM clients WHERE clientID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setObject(1, id);  // UUID
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setClientID(UUID.fromString(rs.getString("clientID")));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setPhone(rs.getString("phone"));
                client.setProfessional(rs.getBoolean("isProfessional"));

                return client;
            } else {
                System.out.println("Client not found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error reading client: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Client client) {
        String query = "UPDATE clients SET name = ?, address = ?, phone = ?, isProfessional = ? WHERE clientID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, client.getName());
            ps.setString(2, client.getAddress());
            ps.setString(3, client.getPhone());
            ps.setBoolean(4, client.isProfessional());
            ps.setObject(5, client.getClientID());  // UUID

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID id) {
        String query = "DELETE FROM clients WHERE clientID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setObject(1, id);  // UUID

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Client client = new Client();
                client.setClientID(UUID.fromString(rs.getString("clientID")));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setPhone(rs.getString("phone"));
                client.setProfessional(rs.getBoolean("isProfessional"));

                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving clients: " + e.getMessage());
        }

        return clients;
    }
}

