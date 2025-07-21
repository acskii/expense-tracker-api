package com.acskii.api.transactions.repo;

import com.acskii.api.transactions.data.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserEmail(String email);
}
