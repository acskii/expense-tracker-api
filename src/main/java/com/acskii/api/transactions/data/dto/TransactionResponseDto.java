package com.acskii.api.transactions.data.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponseDto(
        UUID id,
        String name,
        String description,
        BigDecimal amount
) {
}
