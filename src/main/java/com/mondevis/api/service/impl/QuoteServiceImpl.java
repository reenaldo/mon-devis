package com.mondevis.api.service.impl;

import com.mondevis.api.dto.QuoteRequest;
import com.mondevis.api.dto.QuoteResponse;
import com.mondevis.api.dto.mapper.QuoteMapper;
import com.mondevis.api.entity.Client;
import com.mondevis.api.entity.Quote;
import com.mondevis.api.entity.QuoteStatus;
import com.mondevis.api.exception.ResourceNotFoundException;
import com.mondevis.api.repository.ClientRepository;
import com.mondevis.api.repository.QuoteRepository;
import com.mondevis.api.service.QuoteService;
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
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final ClientRepository clientRepository;
    private final QuoteMapper quoteMapper;

    @Override
    @Transactional
    public QuoteResponse createQuote(QuoteRequest request) {
        log.debug("Creating quote for client id: {}", request.getClientId());

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", request.getClientId()));

        Quote quote = Quote.builder()
                .date(request.getDate())
                .status(QuoteStatus.DRAFT)
                .client(client)
                .build();

        Quote saved = quoteRepository.save(quote);

        log.info("Quote created with id: {}", saved.getId());
        return quoteMapper.toResponse(saved);
    }

    @Override
    public QuoteResponse getQuoteById(Long id) {
        log.debug("Fetching quote with id: {}", id);
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote", id));
        return quoteMapper.toResponse(quote);
    }

    @Override
    public List<QuoteResponse> getQuotesByClientId(Long clientId) {
        log.debug("Fetching quotes for client id: {}", clientId);

        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Client", clientId);
        }

        return quoteRepository.findByClientId(clientId).stream()
                .map(quoteMapper::toResponse)
                .collect(Collectors.toList());
    }
}
