package com.acskii.api.transactions.exception;

/*
    Raise this exception if a transaction search does not return a Transaction response.
*/
public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
