package com.acskii.api.transactions.mapper;

import com.acskii.api.enums.transactions.method.service.PaymentMethodService;
import com.acskii.api.enums.transactions.type.service.TransactionTypeService;
import com.acskii.api.transactions.data.Transaction;
import com.acskii.api.transactions.data.dto.TransactionDto;
import com.acskii.api.transactions.data.dto.TransactionResponseDto;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    private final TransactionTypeService typeService;
    private final PaymentMethodService methodService;

    public TransactionMapper(TransactionTypeService typeService, PaymentMethodService methodService) {
        this.typeService = typeService;
        this.methodService = methodService;
    }

    public TransactionResponseDto toResponseDto(Transaction t) {
        return new TransactionResponseDto(
                t.getId(),
                t.getName(),
                t.getDescription(),
                t.getAmount(),
                t.getType().getType(),
                t.isProfit(),
                t.getLocation(),
                t.getMethod().getMethod()
        );
    }

    public Transaction toNormal(TransactionDto dto) {
        Transaction t = new Transaction();

        t.setName(dto.name());
        if (dto.description() != null) t.setDescription(dto.description());
        if (dto.location() != null) t.setLocation(dto.location());
        t.setAmount(dto.amount());
        t.setType(typeService.getByType(dto.type()));
        t.setMethod(methodService.getByMethod(dto.method()));

        return t;
    }
}
