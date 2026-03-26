package com.mondevis.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteItemRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @Positive(message = "Unit price must be positive")
    private double unitPrice;
}
