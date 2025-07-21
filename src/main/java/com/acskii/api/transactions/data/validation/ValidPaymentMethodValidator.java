package com.acskii.api.transactions.data.validation;

import com.acskii.api.transactions.data.enums.PaymentMethod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPaymentMethodValidator implements ConstraintValidator<ValidPaymentMethod, String> {
    @Override
    public void initialize(ValidPaymentMethod constraint) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        PaymentMethod type = PaymentMethod.toEnum(value);
        return type != null;
    }
}