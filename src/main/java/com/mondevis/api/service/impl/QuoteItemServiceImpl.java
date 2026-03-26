package com.mondevis.api.service.impl;

import com.mondevis.api.dto.QuoteItemRequest;
import com.mondevis.api.dto.QuoteItemResponse;
import com.mondevis.api.dto.mapper.QuoteItemMapper;
import com.mondevis.api.entity.Quote;
import com.mondevis.api.entity.QuoteItem;
import com.mondevis.api.exception.ResourceNotFoundException;
import com.mondevis.api.repository.QuoteItemRepository;
import com.mondevis.api.repository.QuoteRepository;
import com.mondevis.api.service.QuoteItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class QuoteItemServiceImpl implements QuoteItemService {

    private final QuoteItemRepository quoteItemRepository;
    private final QuoteRepository quoteRepository;
    private final QuoteItemMapper quoteItemMapper;

    @Override
    @Transactional
    public QuoteItemResponse addItemToQuote(Long quoteId, QuoteItemRequest request) {
        log.debug("Adding item to quote id: {}", quoteId);

        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResourceNotFoundException("Quote", quoteId));

        QuoteItem item = quoteItemMapper.toEntity(request, quote);
        QuoteItem saved = quoteItemRepository.save(item);

        log.info("Item added to quote {} with item id: {}", quoteId, saved.getId());
        return quoteItemMapper.toResponse(saved);
    }

    @Override
    public List<QuoteItemResponse> getItemsByQuoteId(Long quoteId) {
        log.debug("Fetching items for quote id: {}", quoteId);

        if (!quoteRepository.existsById(quoteId)) {
            throw new ResourceNotFoundException("Quote", quoteId);
        }

        return quoteItemRepository.findByQuoteId(quoteId).stream()
                .map(quoteItemMapper::toResponse)
                .collect(Collectors.toList());
    }
}
