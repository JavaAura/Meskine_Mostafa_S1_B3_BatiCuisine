package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.ProjectDao;
import org.BatiCuisine.Database.DbConnection;
import org.BatiCuisine.Enum.ProjectStatus;
import org.BatiCuisine.Model.Client;
import org.BatiCuisine.Model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectDaoImpl implements ProjectDao {
    private final Connection connection = DbConnection.getInstance();

    @Override
    public void create(Project project) {
        String query = "INSERT INTO projects (projectID, projectName, profitMargin, totalCost, projectStatus, surface, clientID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, project.getProjectID());
            ps.setString(2, project.getProjectName());
            ps.setDouble(3, project.getProfitMargin());
            ps.setDouble(4, project.getTotalCost());
            ps.setObject(5, project.getProjectStatus(), Types.OTHER);
            ps.setDouble(6, project.getSurface());
            ps.setObject(7, project.getClient().getClientID());

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

                String status = rs.getString("projectStatus");
                ProjectStatus projectStatus = ProjectStatus.fromString(status);
                project.setProjectStatus(projectStatus);

                project.setSurface(rs.getDouble("surface"));

                // get client object
                UUID clientID = UUID.fromString(rs.getString("clientID"));
                ClientDaoImpl clientDAO = new ClientDaoImpl();
                Client client = clientDAO.read(clientID);

                project.setClient(client);
            }
            System.out.println("Project retrieved successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving project: " + e.getMessage());
        }
        return project;
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
                String status = rs.getString("projectStatus");

                ProjectStatus projectStatus = ProjectStatus.fromString(status);
                project.setProjectStatus(projectStatus);

                project.setSurface(rs.getDouble("surface"));

                UUID clientID = UUID.fromString(rs.getString("clientID"));
                ClientDaoImpl clientDAO = new ClientDaoImpl();
                Client client = clientDAO.read(clientID);
                project.setClient(client);

                projects.add(project);
            }
            System.out.println("Retrieved all projects successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving projects: " + e.getMessage());
        }
        return projects;
    }
}

