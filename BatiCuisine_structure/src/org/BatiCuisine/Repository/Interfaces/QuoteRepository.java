package org.BatiCuisine.Repository.Interfaces;

import org.BatiCuisine.Model.Quote;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuoteRepository {
    void addQuote(Quote quote);

    Quote getQuoteById(UUID id);

    void updateQuote(Quote quote);

    boolean removeQuote(UUID id);

    List<Quote> getAllQuotes();

    Optional<Quote> getQuoteByProjectID(UUID projectID);
}
