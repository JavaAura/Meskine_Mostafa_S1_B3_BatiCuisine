package org.BatiCuisine.Model;

import org.BatiCuisine.Enum.ComponentType;

import java.util.UUID;

public abstract class Component {
    private UUID componentID;
    private String name;
    private ComponentType componentType;
    private double VATRate;
    private Project project;

    public Component(UUID componentID, String name, ComponentType componentType, double VATRate, Project project) {
        this.componentID = componentID;
        this.name = name;
        this.componentType = componentType;
        this.VATRate = VATRate;
        this.project = project;
    }

    // Getters and Setters
    public UUID getComponentID() {
        return componentID;
    }

    public abstract double calculateComponentCost();

    public void setComponentID(UUID component) {
        this.componentID = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public double getVATRate() {
        return VATRate;
    }

    public void setVATRate(double VATRate) {
        this.VATRate = VATRate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Component{" +
                "componentID=" + componentID +
                ", name='" + name + '\'' +
                ", componentType=" + componentType +
                ", VATRate=" + VATRate +
                ", projectID=" + project.getProjectID() +
                '}';
    }
}
