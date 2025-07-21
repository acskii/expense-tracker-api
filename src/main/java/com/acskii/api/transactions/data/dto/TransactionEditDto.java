package com.acskii.api.transactions.data.dto;

import com.acskii.api.transactions.data.validation.ValidPaymentMethod;
import com.acskii.api.transactions.data.validation.ValidTransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;

public record TransactionEditDto(
        String name,
        String description,
        String location,

        @Digits(integer = 12, fraction = 3, message = "'amount' must be exceed 999999999.999")
        @DecimalMin(value = "0.000", inclusive = false, message = "'amount' must be positive and above 0")
        BigDecimal amount,

        @ValidTransactionType
        String type,

        @ValidPaymentMethod
        String method
) {
}
