package org.BatiCuisine.Model;

import org.BatiCuisine.Enum.ComponentType;

import java.util.UUID;

public class Labor extends Component {
    private double hourlyRate;
    private double workingHours;
    private double workerProductivity;

    public Labor(UUID componentID, String name, double VATRate, UUID projectID, double hourlyRate, double workingHours, double workerProductivity) {
        super(componentID, name, ComponentType.LABOR, VATRate, projectID);
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
        this.workerProductivity = workerProductivity;
    }

    // Getters and Setters
    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }

    @Override
    public String toString() {
        return "Labor{" +
                "hourlyRate=" + hourlyRate +
                ", workingHours=" + workingHours +
                ", workerProductivity=" + workerProductivity +
                "} " + super.toString();
    }
}
