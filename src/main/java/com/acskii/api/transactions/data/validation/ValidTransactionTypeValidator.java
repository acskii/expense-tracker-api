package com.acskii.api.transactions.data.validation;

import com.acskii.api.transactions.data.enums.TransactionType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidTransactionTypeValidator implements ConstraintValidator<ValidTransactionType, String> {
    @Override
    public void initialize(ValidTransactionType constraint) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        TransactionType type = TransactionType.toEnum(value);
        return type != null;
    }
}