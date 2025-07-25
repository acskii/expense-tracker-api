package com.acskii.api.transactions.controller;

import com.acskii.api.enums.transactions.method.exception.PaymentMethodNotFoundException;
import com.acskii.api.enums.transactions.type.exception.TransactionTypeNotFoundException;
import com.acskii.api.transactions.data.Transaction;
import com.acskii.api.transactions.data.dto.TransactionDto;
import com.acskii.api.transactions.data.dto.TransactionEditDto;
import com.acskii.api.transactions.data.dto.TransactionResponseDto;
import com.acskii.api.transactions.exception.TransactionNotFoundException;
import com.acskii.api.transactions.mapper.TransactionMapper;
import com.acskii.api.transactions.service.TransactionService;
import com.acskii.api.users.exception.UserNotAuthorizedException;
import com.acskii.api.users.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service, TransactionMapper mapper) {this.service = service;}

    @GetMapping
    public List<TransactionResponseDto> getTransactions(
            Authentication authentication
    ) {
        return service.getTransactionsFor(authentication.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTransaction(
            @Valid @RequestBody TransactionDto dto,
            Authentication authentication
    ) {
        service.addTransaction(dto, authentication.getName());
    }

    @GetMapping("/{transaction-uuid}")
    public TransactionResponseDto getTransaction(
            @PathVariable("transaction-uuid") UUID id,
            Authentication authentication
            ) {
        return service.getSingleTransaction(id, authentication.getName());
    }

    @PatchMapping("/{transaction-uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void edit(
            @PathVariable("transaction-uuid") UUID id,
            @Valid @RequestBody TransactionEditDto dto,
            Authentication authentication
    ) {
        service.update(id, dto, authentication.getName());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var msg = error.getDefaultMessage();
                    errors.put(fieldName, msg);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<?> handleTransactionNotFoundException(
            TransactionNotFoundException ex
    ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionTypeNotFoundException.class)
    public ResponseEntity<?> handleTransactionTypeNotFoundException(
            TransactionTypeNotFoundException ex
    ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentMethodNotFoundException.class)
    public ResponseEntity<?> handlePaymentMethodNotFoundException(
            PaymentMethodNotFoundException ex
    ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(
            UserNotFoundException ex
    ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<?> handleUserNotAuthorizedException(
            UserNotAuthorizedException ex
    ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
