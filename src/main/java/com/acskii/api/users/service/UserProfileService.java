package com.acskii.api.users.service;

import com.acskii.api.transactions.data.Transaction;
import com.acskii.api.users.data.User;
import com.acskii.api.users.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserRepository repository;

    public UserProfileService(UserRepository repository) {
        this.repository = repository;
    }

    public void updateBalance(User user, Transaction transaction) {
        if (transaction.isProfit()) {
            user.setBalance(user.getBalance().add(transaction.getAmount()));
        } else {
            user.setBalance(user.getBalance().subtract(transaction.getAmount()));
        }

        repository.save(user);
    }
}
