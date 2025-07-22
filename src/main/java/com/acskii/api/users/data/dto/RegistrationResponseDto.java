package com.acskii.api.users.data.dto;

import java.math.BigDecimal;

public record RegistrationResponseDto(
        String name,
        String email,
        BigDecimal balance
) {
}
