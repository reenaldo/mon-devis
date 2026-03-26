package com.mondevis.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteItemResponse {

    private Long id;
    private String description;
    private int quantity;
    private double unitPrice;
    private double lineTotal;
}
