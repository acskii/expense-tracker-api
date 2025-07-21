package com.acskii.api.users.service;

import com.acskii.api.jwt.service.JwtService;
import com.acskii.api.users.data.User;
import com.acskii.api.users.data.dto.AuthenticationDto;
import com.acskii.api.users.data.dto.AuthenticationResponseDto;
import com.acskii.api.users.exception.UserNotFoundException;
import com.acskii.api.users.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    private final AuthenticationManager manager;
    private final JwtService jwtService;
    private final UserRepository repository;

    public UserAuthenticationService(AuthenticationManager manager, JwtService jwtService, UserRepository repository) {
        this.manager = manager;
        this.jwtService = jwtService;
        this.repository = repository;
    }

    public AuthenticationResponseDto authenticate(AuthenticationDto dto) {
        manager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        return new AuthenticationResponseDto(
                jwtService.generateToken(dto.email())
        );
    }

    public User getUserByEmail (final String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User of email <%s> is not found", email)
                ));
    }
}
