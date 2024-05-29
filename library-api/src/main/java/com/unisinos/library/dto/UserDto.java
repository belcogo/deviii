package com.unisinos.library.dto;

import com.unisinos.library.model.Role;
import com.unisinos.library.model.User;

public class UserDto {
    public String name;
    public String password;
    public String email;
    public String role;

    public User convertToUser() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.valueOf(role))
                .build();
    }
}
