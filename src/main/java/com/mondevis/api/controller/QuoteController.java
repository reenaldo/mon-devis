package com.mondevis.api.controller;

import com.mondevis.api.dto.QuoteRequest;
import com.mondevis.api.dto.QuoteResponse;
import com.mondevis.api.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
@Slf4j
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteResponse> createQuote(@Valid @RequestBody QuoteRequest request) {
        log.debug("POST /quotes - Creating quote for client: {}", request.getClientId());
        QuoteResponse response = quoteService.createQuote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponse> getQuoteById(@PathVariable Long id) {
        log.debug("GET /quotes/{} - Fetching quote", id);
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<QuoteResponse>> getQuotesByClientId(@PathVariable Long clientId) {
        log.debug("GET /quotes/client/{} - Fetching quotes for client", clientId);
        return ResponseEntity.ok(quoteService.getQuotesByClientId(clientId));
    }
}
