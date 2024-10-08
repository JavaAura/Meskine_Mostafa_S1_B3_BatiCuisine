package org.BatiCuisine.Repository.Impl;

import org.BatiCuisine.Dao.Interfaces.QuoteDao;
import org.BatiCuisine.Model.Quote;
import org.BatiCuisine.Repository.Interfaces.QuoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class QuoteRepositoryImpl implements QuoteRepository {

    private final QuoteDao quoteDao;

    public QuoteRepositoryImpl(QuoteDao quoteDao) {
        this.quoteDao = quoteDao;
    }

    @Override
    public void addQuote(Quote quote) {
        quoteDao.create(quote);
    }

    @Override
    public Quote getQuoteById(UUID id) {
        return quoteDao.read(id);
    }

    @Override
    public void updateQuote(Quote quote) {
        quoteDao.update(quote);
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteDao.getAll();
    }

    public Optional<Quote> getQuoteByProjectID(UUID projectID){
        return quoteDao.getAll()
                .stream()
                .filter(quote -> quote.getProject().getProjectID() == projectID)
                .findFirst();
    }
}
