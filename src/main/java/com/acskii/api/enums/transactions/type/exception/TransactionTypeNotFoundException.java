package com.acskii.api.enums.transactions.type.exception;

public class TransactionTypeNotFoundException extends RuntimeException{
    public TransactionTypeNotFoundException(String message) {
        super(message);
    }
}
