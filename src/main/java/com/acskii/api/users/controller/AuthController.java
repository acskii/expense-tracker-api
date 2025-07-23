package com.acskii.api.users.controller;

import com.acskii.api.users.data.dto.*;
import com.acskii.api.users.exception.UserNotFoundException;
import com.acskii.api.users.mapper.UserMapper;
import com.acskii.api.users.mapper.UserRegistrationMapper;
import com.acskii.api.users.service.UserAuthenticationService;
import com.acskii.api.users.service.UserRegistrationService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRegistrationService registerService;
    private final UserAuthenticationService authService;
    private final UserRegistrationMapper registrationMapper;
    private final UserMapper userMapper;

    public AuthController(UserAuthenticationService authService, UserRegistrationService registerService,
                          UserRegistrationMapper registrationMapper, UserMapper userMapper) {
        this.registerService = registerService;
        this.authService = authService;
        this.registrationMapper = registrationMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationResponseDto register(
            @Valid @RequestBody RegistrationDto dto
    ) {
        return registrationMapper.toResponse(registerService.register(registrationMapper.toNormal(dto)));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto login(
            @Valid @RequestBody AuthenticationDto dto
    ) {
        return authService.authenticate(dto);
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto health(
            Authentication authentication
    ) {
        return userMapper.toResponse(authService.getUserByEmail(authentication.getName()));
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

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(
            ValidationException ex
    ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(
            UserNotFoundException ex
    ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
