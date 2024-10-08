package org.BatiCuisine.Service;

import org.BatiCuisine.Model.Quote;
import org.BatiCuisine.Repository.Interfaces.QuoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void addQuote(Quote quote) {
        quoteRepository.addQuote(quote);
    }

    public Quote getQuoteById(UUID quoteId) {
        return quoteRepository.getQuoteById(quoteId);
    }

    public void updateQuote(Quote quote) {
        quoteRepository.updateQuote(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.getAllQuotes();
    }

    public Optional<Quote> getQuoteByProjectID(UUID quoteId) {
        return quoteRepository.getQuoteByProjectID(quoteId);
    }
}
