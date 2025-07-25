package com.acskii.api.enums.transactions.type.service;

import com.acskii.api.enums.transactions.type.data.TransactionType;
import com.acskii.api.enums.transactions.type.exception.TransactionTypeNotFoundException;
import com.acskii.api.enums.transactions.type.repo.TransactionTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeService {
    private final TransactionTypeRepository repository;

    public TransactionTypeService(TransactionTypeRepository repository) {
        this.repository = repository;
    }

    public TransactionType getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransactionTypeNotFoundException(
                        String.format("Transaction type of ID <%d> is not found", id)
                ));
    }

    public TransactionType getByType(String type) {
        return repository.findByType(type.toUpperCase())
                .orElseThrow(() -> new TransactionTypeNotFoundException(
                        String.format("Transaction type <%s> is not found", type)
                ));
    }
}
