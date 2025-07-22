package com.acskii.api.users.controller;

import com.acskii.api.users.data.dto.UserProfileDto;
import com.acskii.api.users.mapper.UserMapper;
import com.acskii.api.users.service.UserAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ProfileController {

    private final UserAuthenticationService authService;
    private final UserMapper mapper;

    public ProfileController(UserAuthenticationService authService, UserMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto getProfile(
            Authentication authentication
    ) {
        return mapper.toResponse(authService.getUserByEmail(authentication.getName()));
    }
}
