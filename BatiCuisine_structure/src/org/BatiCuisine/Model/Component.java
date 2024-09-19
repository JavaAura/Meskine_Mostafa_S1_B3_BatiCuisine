package org.BatiCuisine.Model;

import org.BatiCuisine.Enum.ComponentType;

import java.util.UUID;

public abstract class Component {
    private UUID componentID;
    private String name;
    private ComponentType componentType;
    private double VATRate;
    private UUID projectID;

    public Component(UUID componentID, String name, ComponentType componentType, double VATRate, UUID projectID) {
        this.componentID = componentID;
        this.name = name;
        this.componentType = componentType;
        this.VATRate = VATRate;
        this.projectID = projectID;
    }

    // Getters and Setters
    public UUID getComponentID() {
        return componentID;
    }

    public void setComponentID(UUID componentID) {
        this.componentID = componentID;
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

    public UUID getProjectID() {
        return projectID;
    }

    public void setProjectID(UUID projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Component{" +
                "componentID=" + componentID +
                ", name='" + name + '\'' +
                ", componentType=" + componentType +
                ", VATRate=" + VATRate +
                ", projectID=" + projectID +
                '}';
    }
}
