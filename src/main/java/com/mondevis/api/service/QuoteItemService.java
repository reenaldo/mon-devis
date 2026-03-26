package com.mondevis.api.service;

import com.mondevis.api.dto.QuoteItemRequest;
import com.mondevis.api.dto.QuoteItemResponse;

import java.util.List;

public interface QuoteItemService {

    QuoteItemResponse addItemToQuote(Long quoteId, QuoteItemRequest request);

    List<QuoteItemResponse> getItemsByQuoteId(Long quoteId);
}
