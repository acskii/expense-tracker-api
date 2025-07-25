package com.acskii.api.enums.transactions.type.repo;

import com.acskii.api.enums.transactions.type.data.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    Optional<TransactionType> findByType(String type);
}
