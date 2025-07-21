package com.acskii.api.users.service;

import com.acskii.api.users.data.User;
import com.acskii.api.users.data.dto.RegistrationDto;
import com.acskii.api.users.data.dto.RegistrationResponseDto;
import com.acskii.api.users.mapper.UserRegistrationMapper;
import com.acskii.api.users.repo.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserRegistrationService(UserRepository repository, PasswordEncoder encoder, UserRegistrationMapper mapper) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public User register(User request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new ValidationException("E-mail is already registered as a user");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        return save(user);
    }

    private User save(User user) {
        return repository.save(user);
    }
}
