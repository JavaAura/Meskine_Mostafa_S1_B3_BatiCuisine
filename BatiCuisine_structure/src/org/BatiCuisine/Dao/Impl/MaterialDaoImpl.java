package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.MaterialDao;
import org.BatiCuisine.Model.Material;
import org.BatiCuisine.Database.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MaterialDaoImpl implements MaterialDao {

    @Override
    public void create(Material material) {
        String query = "INSERT INTO materials (componentID, name, VAT_rate, transportCost, qualityCoefficient, quantity, unitCost, projectID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, material.getComponentID());
            ps.setString(2, material.getName());
            ps.setDouble(3, material.getVATRate());
            ps.setDouble(4, material.getTransportCost());
            ps.setDouble(5, material.getQualityCoefficient());
            ps.setDouble(6, material.getQuantity());
            ps.setDouble(7, material.getUnitCost());
            ps.setObject(8, material.getProjectID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating material: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
    }

    @Override
    public Material read(UUID id) {
        String query = "SELECT * FROM materials WHERE componentID = ?";
        Connection conn = null;
        Material material = new Material();
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
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
                material.setProjectID(UUID.fromString(rs.getString("projectID")));
            }
        } catch (SQLException e) {
            System.out.println("Error reading material: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
        return material;
    }

    @Override
    public void update(Material material) {
        String query = "UPDATE materials SET name = ?, VAT_rate = ?, transportCost = ?, qualityCoefficient = ?, quantity = ?, unitCost = ?, projectID = ? WHERE componentID = ?";
        Connection conn = null;
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, material.getName());
            ps.setDouble(2, material.getVATRate());
            ps.setDouble(3, material.getTransportCost());
            ps.setDouble(4, material.getQualityCoefficient());
            ps.setDouble(5, material.getQuantity());
            ps.setDouble(6, material.getUnitCost());
            ps.setObject(7, material.getProjectID());
            ps.setObject(8, material.getComponentID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating material: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
    }

    @Override
    public boolean delete(UUID id) {
        String query = "DELETE FROM materials WHERE componentID = ?";
        Connection conn = null;
        boolean isDeleted = false;
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            int rowsDeleted = ps.executeUpdate();
            isDeleted = rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting material: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
        return isDeleted;
    }

    @Override
    public List<Material> getAll() {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials";
        Connection conn = null;
        try {
            conn = DbConnection.getInstance();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Material material = new Material();
                material.setComponentID(UUID.fromString(rs.getString("componentID")));
                material.setName(rs.getString("name"));
                material.setVATRate(rs.getDouble("VAT_rate"));
                material.setTransportCost(rs.getDouble("transportCost"));
                material.setQualityCoefficient(rs.getDouble("qualityCoefficient"));
                material.setQuantity(rs.getDouble("quantity"));
                material.setUnitCost(rs.getDouble("unitCost"));
                material.setProjectID(UUID.fromString(rs.getString("projectID")));
                materials.add(material);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all materials: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
        return materials;
    }
}
