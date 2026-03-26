package com.mondevis.api.dto.mapper;

import com.mondevis.api.dto.QuoteItemRequest;
import com.mondevis.api.dto.QuoteItemResponse;
import com.mondevis.api.entity.Quote;
import com.mondevis.api.entity.QuoteItem;
import org.springframework.stereotype.Component;

@Component
public class QuoteItemMapper {

    public QuoteItem toEntity(QuoteItemRequest request, Quote quote) {
        return QuoteItem.builder()
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .quote(quote)
                .build();
    }

    public QuoteItemResponse toResponse(QuoteItem item) {
        return QuoteItemResponse.builder()
                .id(item.getId())
                .description(item.getDescription())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .lineTotal(item.getQuantity() * item.getUnitPrice())
                .build();
    }
}
