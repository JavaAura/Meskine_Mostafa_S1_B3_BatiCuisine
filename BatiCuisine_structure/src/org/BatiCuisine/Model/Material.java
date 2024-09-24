package org.BatiCuisine.Model;

import org.BatiCuisine.Enum.ComponentType;

import java.util.UUID;

public class Material extends Component {
    private double transportCost;
    private double qualityCoefficient;
    private double quantity;
    private double unitCost;

    public Material(UUID componentID, String name, double VATRate, Project projectID, double transportCost, double qualityCoefficient, double quantity, double unitCost) {
        super(componentID, name, ComponentType.MATERIAL, VATRate, projectID);
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public Material() {
        super(UUID.randomUUID(), "", ComponentType.MATERIAL, 0.0, null);
        this.transportCost = 0.0;
        this.qualityCoefficient = 0.0;
        this.quantity = 0.0;
        this.unitCost = 0.0;
    }

    // Getters and Setters
    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public void setQualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public double calculateComponentCost() {
        double materialCost = (quantity * unitCost * qualityCoefficient) + transportCost;
        System.out.println("- "+ getName() +" : "+ materialCost +" € (quantity : "+ getQuantity() +" m², unit cost : "+ getUnitCost() +" €/m², quality : "+ getQualityCoefficient() +", transport : "+ getTransportCost() +" €)");
        return materialCost;
    }

    @Override
    public String toString() {
        return "Material{" +
                "transportCost=" + transportCost +
                ", qualityCoefficient=" + qualityCoefficient +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                "} " + super.toString();
    }
}
