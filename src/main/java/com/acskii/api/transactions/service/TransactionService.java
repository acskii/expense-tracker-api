package com.acskii.api.transactions.service;

import com.acskii.api.enums.transactions.method.service.PaymentMethodService;
import com.acskii.api.enums.transactions.type.service.TransactionTypeService;
import com.acskii.api.transactions.data.Transaction;
import com.acskii.api.transactions.data.dto.TransactionEditDto;
import com.acskii.api.transactions.data.dto.TransactionResponseDto;
import com.acskii.api.transactions.data.dto.TransactionDto;
import com.acskii.api.transactions.exception.TransactionNotFoundException;
import com.acskii.api.transactions.mapper.TransactionMapper;
import com.acskii.api.transactions.repo.TransactionRepository;
import com.acskii.api.users.data.User;
import com.acskii.api.users.exception.UserNotAuthorizedException;
import com.acskii.api.users.service.UserAuthenticationService;
import com.acskii.api.users.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final UserAuthenticationService authService;
    private final UserProfileService profileService;
    private final TransactionTypeService typeService;
    private final PaymentMethodService methodService;

    public TransactionService(TransactionRepository repository, TransactionMapper mapper,
                              UserAuthenticationService authService, UserProfileService profileService, TransactionTypeService typeService, PaymentMethodService methodService) {
        this.repository = repository;
        this.mapper = mapper;
        this.authService = authService;
        this.profileService = profileService;
        this.typeService = typeService;
        this.methodService = methodService;
    }

    @Deprecated
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public List<TransactionResponseDto> getTransactionsFor(String email) {
        return repository.findByUserEmail(email).stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    public void addTransaction(TransactionDto dto, String email) {
        User user = authService.getUserByEmail(email);
        Transaction t = mapper.toNormal(dto);
        t.setUser(user);

        Transaction saved = save(t);
        profileService.updateBalance(user, saved);
    }

    public TransactionResponseDto getSingleTransaction(UUID id, String email) {
        User user = authService.getUserByEmail(email);
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(
                        String.format("Transaction with ID <%s> not found", id)
                ));

        if (!transaction.getUser().equals(user)) throw new UserNotAuthorizedException(
                String.format("User not authorized to access this transaction of id <%s>", id)
        );

        return mapper.toResponseDto(transaction);
    }

    public void update(UUID id, TransactionEditDto dto, String email) {
        User user = authService.getUserByEmail(email);
        Transaction t = repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(
                        String.format("Transaction with ID <%s> not found", id)
                ));

        if (!t.getUser().equals(user)) throw new UserNotAuthorizedException(
                String.format("User not authorized to access this transaction of id <%s>", id)
        );

        if (dto.name() != null && !dto.name().isBlank()) {t.setName(dto.name());}
        if (dto.description() != null) {t.setDescription(dto.description());}
        if (dto.location() != null) {t.setLocation(dto.location());}
        if (dto.amount() != null) {t.setAmount(dto.amount());}
        if (dto.type() != null) {t.setType(typeService.getByType(dto.type()));}
        if (dto.method() != null) {t.setMethod(methodService.getByMethod(dto.method()));}

        Transaction saved = save(t);
        if (dto.amount() != null) profileService.updateBalance(user, saved);
    }

    private Transaction save(Transaction t) {
        /* If any process needed before save is done here */
        return repository.save(t);
    }

}
