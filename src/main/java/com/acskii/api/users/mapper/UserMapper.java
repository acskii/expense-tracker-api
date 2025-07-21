package com.acskii.api.users.mapper;

import com.acskii.api.users.data.User;
import com.acskii.api.users.data.dto.UserProfileDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserProfileDto toResponse(User user) {
        return new UserProfileDto(user.getEmail(), user.getName());
    }
}
