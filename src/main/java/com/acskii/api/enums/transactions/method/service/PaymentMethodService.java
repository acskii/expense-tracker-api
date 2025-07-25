package com.acskii.api.enums.transactions.method.service;

import com.acskii.api.enums.transactions.method.data.PaymentMethod;
import com.acskii.api.enums.transactions.method.exception.PaymentMethodNotFoundException;
import com.acskii.api.enums.transactions.method.repo.PaymentMethodRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService {
    private final PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    public PaymentMethod getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PaymentMethodNotFoundException(
                        String.format("Payment method of ID <%d> is not found", id)
                ));
    }

    public PaymentMethod getByMethod(String method) {
        return repository.findByMethod(method.toUpperCase())
                .orElseThrow(() -> new PaymentMethodNotFoundException(
                        String.format("Payment method <%s> is not found", method)
                ));
    }
}
