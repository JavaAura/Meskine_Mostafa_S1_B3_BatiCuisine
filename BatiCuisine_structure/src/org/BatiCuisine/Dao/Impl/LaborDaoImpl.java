package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.LaborDao;
import org.BatiCuisine.Model.Labor;
import org.BatiCuisine.Database.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaborDaoImpl implements LaborDao {

    @Override
    public void create(Labor labor) {
        String query = "INSERT INTO labors (componentID, name, VAT_rate, hourlyRate, workingHours, workerProductivity, projectID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, labor.getComponentID());
            ps.setString(2, labor.getName());
            ps.setDouble(3, labor.getVATRate());
            ps.setDouble(4, labor.getHourlyRate());
            ps.setDouble(5, labor.getWorkingHours());
            ps.setDouble(6, labor.getWorkerProductivity());
            ps.setObject(7, labor.getProjectID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating labor: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
    }

    @Override
    public Labor read(UUID id) {
        String query = "SELECT * FROM labors WHERE componentID = ?";
        Connection conn = null;
        Labor labor = new Labor();
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                labor.setComponentID(UUID.fromString(rs.getString("componentID")));
                labor.setName(rs.getString("name"));
                labor.setVATRate(rs.getDouble("VAT_rate"));
                labor.setHourlyRate(rs.getDouble("hourlyRate"));
                labor.setWorkingHours(rs.getDouble("workingHours"));
                labor.setWorkerProductivity(rs.getDouble("workerProductivity"));
                labor.setProjectID(UUID.fromString(rs.getString("projectID")));
            }
        } catch (SQLException e) {
            System.out.println("Error reading labor: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
        return labor;
    }

    @Override
    public void update(Labor labor) {
        String query = "UPDATE labors SET name = ?, VAT_rate = ?, hourlyRate = ?, workingHours = ?, workerProductivity = ?, projectID = ? WHERE componentID = ?";
        Connection conn = null;
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, labor.getName());
            ps.setDouble(2, labor.getVATRate());
            ps.setDouble(3, labor.getHourlyRate());
            ps.setDouble(4, labor.getWorkingHours());
            ps.setDouble(5, labor.getWorkerProductivity());
            ps.setObject(6, labor.getProjectID());
            ps.setObject(7, labor.getComponentID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating labor: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
    }

    @Override
    public boolean delete(UUID id) {
        String query = "DELETE FROM labors WHERE componentID = ?";
        Connection conn = null;
        boolean isDeleted = false;
        try {
            conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            int rowsDeleted = ps.executeUpdate();
            isDeleted = rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting labor: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
        return isDeleted;
    }

    @Override
    public List<Labor> getAll() {
        List<Labor> labors = new ArrayList<>();
        String query = "SELECT * FROM labors";
        Connection conn = null;
        try {
            conn = DbConnection.getInstance();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Labor labor = new Labor();
                labor.setComponentID(UUID.fromString(rs.getString("componentID")));
                labor.setName(rs.getString("name"));
                labor.setVATRate(rs.getDouble("VAT_rate"));
                labor.setHourlyRate(rs.getDouble("hourlyRate"));
                labor.setWorkingHours(rs.getDouble("workingHours"));
                labor.setWorkerProductivity(rs.getDouble("workerProductivity"));
                labor.setProjectID(UUID.fromString(rs.getString("projectID")));
                labors.add(labor);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all labors: " + e.getMessage());
        } finally {
            if (conn != null) {
                DbConnection.closeConnection();
            }
        }
        return labors;
    }
}
