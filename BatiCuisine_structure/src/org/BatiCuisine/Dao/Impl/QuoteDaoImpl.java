package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.QuoteDao;
import org.BatiCuisine.Database.DbConnection;
import org.BatiCuisine.Model.Project;
import org.BatiCuisine.Model.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuoteDaoImpl implements QuoteDao {
    private final Connection connection = DbConnection.getInstance();

    @Override
    public void create(Quote quote) {
        String query = "INSERT INTO quotes (quoteID, estimatedAmount, issueDate, validityDate, isAccepted, projectID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, quote.getQuoteID());
            ps.setDouble(2, quote.getEstimatedAmount());
            ps.setDate(3, java.sql.Date.valueOf(quote.getIssueDate()));
            ps.setDate(4, java.sql.Date.valueOf(quote.getValidityDate()));
            ps.setBoolean(5, quote.isAccepted());
            ps.setObject(6, quote.getProject().getProjectID());
            ps.executeUpdate();
            System.out.println("Quote created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating quote: " + e.getMessage());
        }
    }

    @Override
    public Quote read(UUID id) {
        String query = "SELECT * FROM quotes WHERE quoteID = ?";
        Quote quote = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quote = new Quote();
                quote.setQuoteID(UUID.fromString(rs.getString("quoteID")));
                quote.setEstimatedAmount(rs.getDouble("estimatedAmount"));
                quote.setIssueDate(rs.getDate("issueDate").toLocalDate());
                quote.setValidityDate(rs.getDate("validityDate").toLocalDate());
                quote.setAccepted(rs.getBoolean("isAccepted"));

                UUID projectID = UUID.fromString(rs.getString("projectID"));
                ProjectDaoImpl projectDao = new ProjectDaoImpl();
                Project project = projectDao.read(projectID);
                quote.setProject(project);
            }
            System.out.println("Quote retrieved successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving quote: " + e.getMessage());
        }
        return quote;
    }

    @Override
    public void update(Quote quote) {
        String query = "UPDATE quotes SET estimatedAmount = ?, issueDate = ?, validityDate = ?, isAccepted = ?, projectID = ? WHERE quoteID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDouble(1, quote.getEstimatedAmount());
            ps.setDate(3, java.sql.Date.valueOf(quote.getIssueDate()));
            ps.setDate(4, java.sql.Date.valueOf(quote.getValidityDate()));
            ps.setBoolean(4, quote.isAccepted());
            ps.setObject(5, quote.getProject().getProjectID());
            ps.setObject(6, quote.getQuoteID());
            ps.executeUpdate();
            System.out.println("Quote updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating quote: " + e.getMessage());
        }
    }

    @Override
    public List<Quote> getAll() {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT * FROM quotes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Quote quote = new Quote();
                quote.setQuoteID(UUID.fromString(rs.getString("quoteID")));
                quote.setEstimatedAmount(rs.getDouble("estimatedAmount"));
                quote.setIssueDate(rs.getDate("issueDate").toLocalDate());
                quote.setValidityDate(rs.getDate("validityDate").toLocalDate());
                quote.setAccepted(rs.getBoolean("isAccepted"));

                UUID projectID = UUID.fromString(rs.getString("projectID"));
                ProjectDaoImpl projectDao = new ProjectDaoImpl();
                Project project = projectDao.read(projectID);
                quote.setProject(project);

                quotes.add(quote);
            }
            System.out.println("Retrieved all quotes successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving quotes: " + e.getMessage());
        }
        return quotes;
    }
}
