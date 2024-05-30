package com.unisinos.library.controller;

import com.unisinos.library.dto.ErrorMessageResponse;
import com.unisinos.library.dto.UserDto;
import com.unisinos.library.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserDto userRequest) {
        var user = userRequest.convertToUser();
        var userCreated = userService.registerUser(user);

        if (userCreated.isEmpty()) {
            var error =
                    ErrorMessageResponse.builder()
                    .errorCode("USER_001")
                    .message("User already register")
                            .build();

            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.created(URI.create("/users/" + userCreated.get().id)).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<?> listUsers() {
        var user = userService.getUsers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        var user = userService.getUser(id);

        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user.get());
    }
}