package org.BatiCuisine.Model;

import org.BatiCuisine.Enum.ProjectStatus;

import java.util.UUID;

public class Project {
    private UUID projectID;
    private String projectName;
    private double profitMargin;
    private double totalCost;
    private ProjectStatus projectStatus;
    private double surface;
    private Client client;

    // Constructors
    public Project(UUID projectID, String projectName, double profitMargin, double totalCost, ProjectStatus projectStatus, double surface, Client client) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.surface = surface;
        this.client = client;
    }

    // Default constructor
    public Project() {
        this.projectID = UUID.randomUUID();
    }

    // Getters and Setters
    public UUID getProjectID() {
        return projectID;
    }

    public void setProjectID(UUID projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void showDetails() {
        System.out.println("*******************************");
        System.out.println("       Project Details         ");
        System.out.println("*******************************");
        System.out.println("Project Name: " + projectName);
        System.out.println("Surface: " + surface + " m²");
        System.out.println("Profit Margin: " + profitMargin);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Project Status: " + projectStatus);
        System.out.println("Client Name: " + client.getName());
    }


    @Override
    public String toString() {
        return "Project{" +
                "projectName : '" + projectName + '\'' +
                ", profitMargin : " + profitMargin +
                ", totalCost : " + totalCost +
                ", projectStatus : '" + projectStatus + '\'' +
                ", surface : " + surface +
                '}';
    }
}
