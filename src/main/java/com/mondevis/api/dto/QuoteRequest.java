package com.mondevis.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteRequest {

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Client ID is required")
    private Long clientId;
}
