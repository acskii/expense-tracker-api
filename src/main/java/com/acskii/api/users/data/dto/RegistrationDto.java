package com.acskii.api.users.data.dto;

import jakarta.validation.constraints.NotNull;

// TODO: other username/email constraints and validation
public record RegistrationDto(
        @NotNull(message = "'name' is a required field")
        String name,

        @NotNull(message = "'email' is a required field")
        String email,

        @NotNull(message = "'password' is a required field")
        String password
) {
}
