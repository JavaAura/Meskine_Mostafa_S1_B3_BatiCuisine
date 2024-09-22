package org.BatiCuisine.Model;

import java.util.UUID;
import java.util.Date;

public class Quote {
    private UUID quoteID;
    private double estimatedAmount;
    private Date issueDate;
    private Date validityDate;
    private boolean isAccepted;
    private UUID projectID;

    public Quote() {
    }

    public Quote(UUID quoteID, double estimatedAmount, Date issueDate, Date validityDate, boolean isAccepted, UUID projectID) {
        this.quoteID = quoteID;
        this.estimatedAmount = estimatedAmount;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
        this.isAccepted = isAccepted;
        this.projectID = projectID;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public UUID getProjectID() {
        return projectID;
    }

    public void setProjectID(UUID projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteID=" + quoteID +
                ", estimatedAmount=" + estimatedAmount +
                ", issueDate=" + issueDate +
                ", validityDate=" + validityDate +
                ", isAccepted=" + isAccepted +
                ", projectID=" + projectID +
                '}';
    }
}
