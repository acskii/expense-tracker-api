package com.acskii.api.transactions.data.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record TransactionDto(
        @NotEmpty(message = "'name' parameter must not be EMPTY")
        @NotNull(message = "'name' parameter must not be NULL")
        String name,

        String description,

        @NotNull(message = "'amount' parameter must not be NULL")
        @Digits(integer = 12, fraction = 3, message = "'amount' must be exceed 999999999.999")
        @DecimalMin(value = "0.000", inclusive = false, message = "'amount' must be positive and above 0")
        BigDecimal amount,

        @NotNull(message = "'type' parameter must not be NULL")
        String type,

        String location,

        @NotNull(message = "'method' parameter must not be NULL")
        String method
) {
}
