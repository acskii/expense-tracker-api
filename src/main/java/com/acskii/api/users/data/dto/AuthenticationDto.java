package com.acskii.api.users.data.dto;

// TODO: Validation of email
public record AuthenticationDto(
        String email,
        String password
) {
}
