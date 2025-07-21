package com.acskii.api.transactions.mapper;

import com.acskii.api.transactions.data.Transaction;
import com.acskii.api.transactions.data.dto.TransactionDto;
import com.acskii.api.transactions.data.dto.TransactionResponseDto;
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
                t.isProfit()
        );
    }

    public Transaction toNormal(TransactionDto dto) {
        Transaction t = new Transaction();

        t.setName(dto.name());
        t.setDescription(dto.description());
        t.setAmount(dto.amount());
        t.setType(TransactionType.toEnum(dto.type()));

        return t;
    }
}
