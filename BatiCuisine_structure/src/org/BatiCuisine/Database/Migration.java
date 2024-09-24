package org.BatiCuisine.Database;

import java.sql.Connection;
import java.sql.Statement;

public class Migration {

    private Migration() {
    }

    public static void createDatabase() {
        Connection conn = DbConnection.getInstance();
        createClientTable(conn);
        createProjectTable(conn);
        createQuoteTable(conn);
        createComponentTable(conn);
        createMaterialTable(conn);
        createLaborTable(conn);
        DbConnection.closeConnection();
    }

    public static void createClientTable(Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TABLE clients ("
                    + "clientID UUID PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "address VARCHAR(255), "
                    + "phone VARCHAR(15), "
                    + "isProfessional BOOLEAN DEFAULT FALSE)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("clients table created successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createProjectTable(Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TYPE projectStatus AS ENUM ('IN_PROGRESS', 'FINISHED', 'CANCELED');";
            statement = conn.createStatement();
            statement.execute(query);  // Create enum first

            query = "CREATE TABLE projects ("
                    + "projectID UUID PRIMARY KEY, "
                    + "projectName VARCHAR(100) NOT NULL, "
                    + "profitMargin DOUBLE PRECISION, "
                    + "totalCost DOUBLE PRECISION, "
                    + "projectStatus projectStatus DEFAULT 'IN_PROGRESS', "
                    + "surface DOUBLE PRECISION, "
                    + "clientID UUID REFERENCES clients(clientID))";
            statement.execute(query);
            System.out.println("projects table created successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createQuoteTable(Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TABLE quotes ("
                    + "quoteID UUID PRIMARY KEY, "
                    + "estimatedAmount DOUBLE PRECISION, "
                    + "issueDate DATE NOT NULL, "
                    + "validityDate DATE, "
                    + "isAccepted BOOLEAN DEFAULT FALSE, "
                    + "projectID UUID REFERENCES projects(projectID) ON DELETE CASCADE)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("quotes table created successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createComponentTable(Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TYPE componentType AS ENUM ('LABOR', 'MATERIAL');";
            statement = conn.createStatement();
            statement.execute(query);  // Create enum first

            query = "CREATE TABLE components ("
                    + "componentID UUID PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "componentType componentType NOT NULL, "
                    + "VAT_rate DOUBLE PRECISION NOT NULL,"
                    + "projectID UUID REFERENCES projects(projectID) ON DELETE CASCADE)";
            statement.execute(query);
            System.out.println("components table created successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createMaterialTable(Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TABLE materials ("
                    + "transportCost DOUBLE PRECISION, "
                    + "qualityCoefficient DOUBLE PRECISION, "
                    + "quantity DOUBLE PRECISION, "
                    + "unitCost DOUBLE PRECISION"
                    + ") INHERITS (components)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("materials table created successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void createLaborTable(Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TABLE labors ("
                    + "hourlyRate DOUBLE PRECISION, "
                    + "workingHours DOUBLE PRECISION, "
                    + "workerProductivity DOUBLE PRECISION"
                    + ") INHERITS (components)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("labors table created successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
