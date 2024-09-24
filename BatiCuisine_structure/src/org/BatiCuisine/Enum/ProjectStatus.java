package org.BatiCuisine.Enum;

public enum ProjectStatus {
    IN_PROGRESS,
    FINISHED,
    CANCELED;

    public static ProjectStatus fromString(String status) {
        switch (status.toUpperCase()) {
            case "IN_PROGRESS":
                return IN_PROGRESS;
            case "FINISHED":
                return FINISHED;
            case "CANCELED":
                return CANCELED;
            default:
                throw new IllegalArgumentException("Unknown project status: " + status);
        }
    }
}
