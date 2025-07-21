package com.acskii.api.transactions.data.enums.converter;

import com.acskii.api.transactions.data.enums.PaymentMethod;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {

    @Override
    public String convertToDatabaseColumn(PaymentMethod type) {
        return type.getValue();
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String code) {
        if (code == null) return null;
        return PaymentMethod.toEnum(code);
    }
}
