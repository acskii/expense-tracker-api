package com.acskii.api.users.mapper;

import com.acskii.api.users.data.User;
import com.acskii.api.users.data.dto.RegistrationDto;
import com.acskii.api.users.data.dto.RegistrationResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationMapper {
    public User toNormal(RegistrationDto dto) {
        User user = new User();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        return user;
    }

    public RegistrationResponseDto toResponse(User user) {
        return new RegistrationResponseDto(
               user.getName(),
               user.getEmail(),
               user.getBalance()
        );
    }
}
