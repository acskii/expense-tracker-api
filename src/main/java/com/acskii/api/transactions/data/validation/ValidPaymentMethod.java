package com.acskii.api.transactions.data.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPaymentMethodValidator.class)
public @interface ValidPaymentMethod {
    String message() default "Invalid payment method";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}