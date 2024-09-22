package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.ProjectDao;
import org.BatiCuisine.Model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectDaoImpl implements ProjectDao {
    private final Connection connection;

    public ProjectDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Project project) {
        String query = "INSERT INTO projects (projectID, projectName, profitMargin, totalCost, projectStatus, surface, clientID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, project.getProjectID());
            ps.setString(2, project.getProjectName());
            ps.setDouble(3, project.getProfitMargin());
            ps.setDouble(4, project.getTotalCost());
            ps.setString(5, project.getProjectStatus());
            ps.setDouble(6, project.getSurface());
            ps.setObject(7, project.getClientID());
            ps.executeUpdate();
            System.out.println("Project created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating project: " + e.getMessage());
        }
    }

    @Override
    public Project read(UUID id) {
        String query = "SELECT * FROM projects WHERE projectID = ?";
        Project project = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                project = new Project();
                project.setProjectID(UUID.fromString(rs.getString("projectID")));
                project.setProjectName(rs.getString("projectName"));
                project.setProfitMargin(rs.getDouble("profitMargin"));
                project.setTotalCost(rs.getDouble("totalCost"));
                project.setProjectStatus(rs.getString("projectStatus"));
                project.setSurface(rs.getDouble("surface"));
                project.setClientID(UUID.fromString(rs.getString("clientID")));
            }
            System.out.println("Project retrieved successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving project: " + e.getMessage());
        }
        return project;
    }

    @Override
    public void update(Project project) {
        String query = "UPDATE projects SET projectName = ?, profitMargin = ?, totalCost = ?, projectStatus = ?, surface = ?, clientID = ? WHERE projectID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, project.getProjectName());
            ps.setDouble(2, project.getProfitMargin());
            ps.setDouble(3, project.getTotalCost());
            ps.setString(4, project.getProjectStatus());
            ps.setDouble(5, project.getSurface());
            ps.setObject(6, project.getClientID());
            ps.setObject(7, project.getProjectID());
            ps.executeUpdate();
            System.out.println("Project updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating project: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID id) {
        String query = "DELETE FROM projects WHERE projectID = ?";
        boolean isDeleted = false;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            int rowsAffected = ps.executeUpdate();
            isDeleted = rowsAffected > 0;
            System.out.println("Project deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting project: " + e.getMessage());
        }
        return isDeleted;
    }

    @Override
    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(UUID.fromString(rs.getString("projectID")));
                project.setProjectName(rs.getString("projectName"));
                project.setProfitMargin(rs.getDouble("profitMargin"));
                project.setTotalCost(rs.getDouble("totalCost"));
                project.setProjectStatus(rs.getString("projectStatus"));
                project.setSurface(rs.getDouble("surface"));
                project.setClientID(UUID.fromString(rs.getString("clientID")));
                projects.add(project);
            }
            System.out.println("Retrieved all projects successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving projects: " + e.getMessage());
        }
        return projects;
    }
}

