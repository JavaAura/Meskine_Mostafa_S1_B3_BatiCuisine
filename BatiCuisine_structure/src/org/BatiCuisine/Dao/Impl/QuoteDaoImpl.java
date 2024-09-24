package org.BatiCuisine.Dao.Impl;

import org.BatiCuisine.Dao.Interfaces.QuoteDao;
import org.BatiCuisine.Model.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuoteDaoImpl implements QuoteDao {
    private final Connection connection;

    public QuoteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Quote quote) {
        String query = "INSERT INTO quotes (quoteID, estimatedAmount, issueDate, validityDate, isAccepted, projectID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, quote.getQuoteID());
            ps.setDouble(2, quote.getEstimatedAmount());
            ps.setDate(3, new java.sql.Date(quote.getIssueDate().getTime()));
            ps.setDate(4, new java.sql.Date(quote.getValidityDate().getTime()));
            ps.setBoolean(5, quote.isAccepted());
            ps.setObject(6, quote.getProject());
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
                quote.setIssueDate(rs.getDate("issueDate"));
                quote.setValidityDate(rs.getDate("validityDate"));
                quote.setAccepted(rs.getBoolean("isAccepted"));
                quote.setProject(UUID.fromString(rs.getString("projectID")));
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
            ps.setDate(2, new java.sql.Date(quote.getIssueDate().getTime()));
            ps.setDate(3, new java.sql.Date(quote.getValidityDate().getTime()));
            ps.setBoolean(4, quote.isAccepted());
            ps.setObject(5, quote.getProject());
            ps.setObject(6, quote.getQuoteID());
            ps.executeUpdate();
            System.out.println("Quote updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating quote: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID id) {
        String query = "DELETE FROM quotes WHERE quoteID = ?";
        boolean isDeleted = false;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            int rowsAffected = ps.executeUpdate();
            isDeleted = rowsAffected > 0;
            System.out.println("Quote deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting quote: " + e.getMessage());
        }
        return isDeleted;
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
                quote.setIssueDate(rs.getDate("issueDate"));
                quote.setValidityDate(rs.getDate("validityDate"));
                quote.setAccepted(rs.getBoolean("isAccepted"));
//                quote.setProject(UUID.fromString(rs.getString("projectID")));
                quotes.add(quote);
            }
            System.out.println("Retrieved all quotes successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving quotes: " + e.getMessage());
        }
        return quotes;
    }
}
