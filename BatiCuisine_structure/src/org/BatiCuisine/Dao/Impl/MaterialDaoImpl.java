package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.MaterialDao;
import org.BatiCuisine.Database.DbConnection;
import org.BatiCuisine.Model.Material;
import org.BatiCuisine.Model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MaterialDaoImpl implements MaterialDao {
    private final Connection connection = DbConnection.getInstance();

    @Override
    public void create(Material material) {
        String query = "INSERT INTO materials (componentID, name, componentType, VAT_rate, transportCost, qualityCoefficient, quantity, unitCost, projectID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, material.getComponentID());
            ps.setString(2, material.getName());
            ps.setObject(3, material.getComponentType(), Types.OTHER);
            ps.setDouble(4, material.getVATRate());
            ps.setDouble(5, material.getTransportCost());
            ps.setDouble(6, material.getQualityCoefficient());
            ps.setDouble(7, material.getQuantity());
            ps.setDouble(8, material.getUnitCost());
            ps.setObject(9, material.getProject().getProjectID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating material: " + e.getMessage());
        }
    }

    @Override
    public Material read(UUID id) {
        String query = "SELECT * FROM materials WHERE componentID = ?";
        Material material = new Material();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                material.setComponentID(UUID.fromString(rs.getString("componentID")));
                material.setName(rs.getString("name"));
                material.setVATRate(rs.getDouble("VAT_rate"));
                material.setTransportCost(rs.getDouble("transportCost"));
                material.setQualityCoefficient(rs.getDouble("qualityCoefficient"));
                material.setQuantity(rs.getDouble("quantity"));
                material.setUnitCost(rs.getDouble("unitCost"));

                UUID projectID = UUID.fromString(rs.getString("projectID"));
                ProjectDaoImpl projectDao = new ProjectDaoImpl();
                Project project = projectDao.read(projectID);
                material.setProject(project);
            }
        } catch (SQLException e) {
            System.out.println("Error reading material: " + e.getMessage());
        }
        return material;
    }

    @Override
    public List<Material> getAll() {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Material material = new Material();
                material.setComponentID(UUID.fromString(rs.getString("componentID")));
                material.setName(rs.getString("name"));
                material.setVATRate(rs.getDouble("VAT_rate"));
                material.setTransportCost(rs.getDouble("transportCost"));
                material.setQualityCoefficient(rs.getDouble("qualityCoefficient"));
                material.setQuantity(rs.getDouble("quantity"));
                material.setUnitCost(rs.getDouble("unitCost"));

                UUID projectID = UUID.fromString(rs.getString("projectID"));
                ProjectDaoImpl projectDao = new ProjectDaoImpl();
                Project project = projectDao.read(projectID);
                material.setProject(project);

                materials.add(material);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all materials: " + e.getMessage());
        }
        return materials;
    }
}

