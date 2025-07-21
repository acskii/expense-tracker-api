package com.acskii.api.transactions.data.enums.converter;

import com.acskii.api.transactions.data.enums.TransactionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {

    @Override
    public String convertToDatabaseColumn(TransactionType type) {
        return type.getValue();
    }

    @Override
    public TransactionType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TransactionType.values())
                .filter(c -> c.getValue().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
