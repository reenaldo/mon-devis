package com.mondevis.api.service;

import com.mondevis.api.dto.QuoteRequest;
import com.mondevis.api.dto.QuoteResponse;

import java.util.List;

public interface QuoteService {

    QuoteResponse createQuote(QuoteRequest request);

    QuoteResponse getQuoteById(Long id);

    List<QuoteResponse> getQuotesByClientId(Long clientId);
}
