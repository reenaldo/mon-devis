package com.mondevis.api.controller;

import com.mondevis.api.dto.QuoteItemRequest;
import com.mondevis.api.dto.QuoteItemResponse;
import com.mondevis.api.service.QuoteItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes/{quoteId}/items")
@RequiredArgsConstructor
@Slf4j
public class QuoteItemController {

    private final QuoteItemService quoteItemService;

    @PostMapping
    public ResponseEntity<QuoteItemResponse> addItem(
            @PathVariable Long quoteId,
            @Valid @RequestBody QuoteItemRequest request) {
        log.debug("POST /quotes/{}/items - Adding item", quoteId);
        QuoteItemResponse response = quoteItemService.addItemToQuote(quoteId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<QuoteItemResponse>> getItems(@PathVariable Long quoteId) {
        log.debug("GET /quotes/{}/items - Fetching items", quoteId);
        return ResponseEntity.ok(quoteItemService.getItemsByQuoteId(quoteId));
    }
}
