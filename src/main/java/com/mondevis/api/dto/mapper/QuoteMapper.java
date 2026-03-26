package com.mondevis.api.dto.mapper;

import com.mondevis.api.dto.QuoteResponse;
import com.mondevis.api.entity.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuoteMapper {

    private final QuoteItemMapper quoteItemMapper;

    public QuoteResponse toResponse(Quote quote) {
        var items = quote.getItems() != null
                ? quote.getItems().stream().map(quoteItemMapper::toResponse).collect(Collectors.toList())
                : Collections.<com.mondevis.api.dto.QuoteItemResponse>emptyList();

        return QuoteResponse.builder()
                .id(quote.getId())
                .date(quote.getDate())
                .status(quote.getStatus())
                .clientId(quote.getClient().getId())
                .clientName(quote.getClient().getName())
                .items(items)
                .totalPrice(quote.getTotalPrice())
                .build();
    }
}
