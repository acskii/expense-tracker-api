package com.acskii.api.transactions.data.enums;

import java.util.stream.Stream;

public enum PaymentMethod {
    CASH("Cash"),
    CREDIT_CARD("Card");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static PaymentMethod toEnum(String value) {
        return Stream.of(PaymentMethod.values())
                .filter(c -> c.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

    public String getValue() {
        return this.value;
    }
}
