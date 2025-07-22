package com.acskii.api.users.data.dto;

import java.math.BigDecimal;

public record UserProfileDto(
        String email,
        String name,
        BigDecimal balance
) {
}
