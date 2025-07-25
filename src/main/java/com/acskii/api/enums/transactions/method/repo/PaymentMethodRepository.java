package com.acskii.api.enums.transactions.method.repo;

import com.acskii.api.enums.transactions.method.data.PaymentMethod;
import com.acskii.api.enums.transactions.type.data.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findByMethod(String method);
}
