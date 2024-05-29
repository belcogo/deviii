package com.unisinos.library.controller;

import com.unisinos.library.dto.UserDto;
import com.unisinos.library.model.User;
import com.unisinos.library.repository.UserRepository;
import com.unisinos.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserDto userRequest) {
        var user = userRequest.convertToUser();
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}