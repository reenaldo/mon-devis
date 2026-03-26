package com.mondevis.api.dto;

import com.mondevis.api.entity.QuoteStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteResponse {

    private Long id;
    private LocalDate date;
    private QuoteStatus status;
    private Long clientId;
    private String clientName;
    private List<QuoteItemResponse> items;
    private double totalPrice;
}
