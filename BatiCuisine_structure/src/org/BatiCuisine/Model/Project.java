package org.BatiCuisine.Model;

import java.util.UUID;

public class Project {
    private UUID projectID;
    private String projectName;
    private double profitMargin;
    private double totalCost;
    private String projectStatus; // !!!!!!!!!I need to use Enum after
    private double surface;
    private UUID clientID;

    // Constructors
    public Project(UUID projectID, String projectName, double profitMargin, double totalCost, String projectStatus, double surface, UUID clientID) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.surface = surface;
        this.clientID = clientID;
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

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public UUID getClientID() {
        return clientID;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
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
