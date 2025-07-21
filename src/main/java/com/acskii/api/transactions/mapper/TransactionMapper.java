package com.acskii.api.transactions.mapper;

import com.acskii.api.transactions.data.Transaction;
import com.acskii.api.transactions.data.dto.TransactionDto;
import com.acskii.api.transactions.data.dto.TransactionResponseDto;
import com.acskii.api.transactions.data.enums.PaymentMethod;
import com.acskii.api.transactions.data.enums.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    public TransactionResponseDto toResponseDto(Transaction t) {
        return new TransactionResponseDto(
                t.getId(),
                t.getName(),
                t.getDescription(),
                t.getAmount(),
                t.getType().getValue(),
                t.isProfit(),
                t.getLocation(),
                t.getMethod().getValue()
        );
    }

    public Transaction toNormal(TransactionDto dto) {
        Transaction t = new Transaction();

        t.setName(dto.name());
        t.setDescription(dto.description());
        t.setLocation(dto.location());
        t.setAmount(dto.amount());
        t.setType(TransactionType.toEnum(dto.type()));
        t.setMethod(PaymentMethod.toEnum(dto.method()));

        return t;
    }
}
