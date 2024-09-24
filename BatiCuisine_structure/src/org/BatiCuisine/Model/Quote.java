package org.BatiCuisine.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Quote {
    private UUID quoteID;
    private double estimatedAmount;
    private LocalDate issueDate;
    private LocalDate validityDate;
    private boolean isAccepted;
    private Project project;

    public Quote() {
        this.quoteID = UUID.randomUUID();
    }

    public Quote(UUID quoteID, double estimatedAmount, LocalDate issueDate, LocalDate validityDate, boolean isAccepted, Project project) {
        this.quoteID = quoteID;
        this.estimatedAmount = estimatedAmount;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.isAccepted = isAccepted;
        this.project = project;
    }

    public UUID getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(UUID quoteID) {
        this.quoteID = quoteID;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void showDetails() {
        System.out.println("*******************************");
        System.out.println("         Quote Details         ");
        System.out.println("*******************************");
        System.out.println("Estimated Amount: " + estimatedAmount);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Validity Date: " + validityDate);
        System.out.println("Accepted: " + (isAccepted ? "Yes" : "No"));
        System.out.println("Project Name: " + project.getProjectName());
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteID=" + quoteID +
                ", estimatedAmount=" + estimatedAmount +
                ", issueDate=" + issueDate +
                ", validityDate=" + validityDate +
                ", isAccepted=" + isAccepted +
                ", projectID=" + project.getProjectID() +
                '}';
    }
}
