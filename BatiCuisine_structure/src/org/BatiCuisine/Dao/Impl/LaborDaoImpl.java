package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.LaborDao;
import org.BatiCuisine.Database.DbConnection;
import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Model.Labor;
import org.BatiCuisine.Model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaborDaoImpl implements LaborDao {
    private final Connection connection = DbConnection.getInstance();

    @Override
    public void create(Labor labor) {
        String query = "INSERT INTO labors (componentID, name, componentType, VAT_rate, hourlyRate, workingHours, workerProductivity, projectID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, labor.getComponentID());
            ps.setString(2, labor.getName());
            ps.setObject(3, labor.getComponentType(), Types.OTHER);
            ps.setDouble(4, labor.getVATRate());
            ps.setDouble(5, labor.getHourlyRate());
            ps.setDouble(6, labor.getWorkingHours());
            ps.setDouble(7, labor.getWorkerProductivity());
            ps.setObject(8, labor.getProject().getProjectID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating labor: " + e.getMessage());
        }
    }

    @Override
    public Labor read(UUID id) {
        String query = "SELECT * FROM labors WHERE componentID = ?";
        Labor labor = new Labor();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                labor.setComponentID(UUID.fromString(rs.getString("componentID")));
                labor.setName(rs.getString("name"));
                labor.setVATRate(rs.getDouble("VAT_rate"));
                labor.setHourlyRate(rs.getDouble("hourlyRate"));
                labor.setWorkingHours(rs.getDouble("workingHours"));
                labor.setWorkerProductivity(rs.getDouble("workerProductivity"));

                UUID projectID = UUID.fromString(rs.getString("projectID"));
                ProjectDaoImpl projectDao = new ProjectDaoImpl();
                Project project = projectDao.read(projectID);
                labor.setProject(project);

            }
        } catch (SQLException e) {
            System.out.println("Error reading labor: " + e.getMessage());
        }
        return labor;
    }


    @Override
    public List<Labor> getAll() {
        List<Labor> labors = new ArrayList<>();
        String query = "SELECT * FROM labors";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Labor labor = new Labor();
                labor.setComponentID(UUID.fromString(rs.getString("componentID")));
                labor.setName(rs.getString("name"));
                labor.setVATRate(rs.getDouble("VAT_rate"));
                labor.setHourlyRate(rs.getDouble("hourlyRate"));
                labor.setWorkingHours(rs.getDouble("workingHours"));
                labor.setWorkerProductivity(rs.getDouble("workerProductivity"));

                UUID projectID = UUID.fromString(rs.getString("projectID"));
                ProjectDaoImpl projectDao = new ProjectDaoImpl();
                Project project = projectDao.read(projectID);
                labor.setProject(project);

                labors.add(labor);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all labors: " + e.getMessage());
        }
        return labors;
    }
}

