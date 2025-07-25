package com.acskii.api.enums.transactions.method.exception;

public class PaymentMethodNotFoundException extends RuntimeException{
    public PaymentMethodNotFoundException(String message) {
        super(message);
    }
}
