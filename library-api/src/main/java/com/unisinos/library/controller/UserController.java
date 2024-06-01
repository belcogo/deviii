package com.unisinos.library.controller;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.dto.request.UserRequest;
import com.unisinos.library.model.User;
import com.unisinos.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        var errors = userRequest.validate();

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        var user = userRequest.convertToUser();
        var response = userService.registerUser(user);

        if (response.errorAccumulators != null && !response.errorAccumulators.isEmpty()) {
            return ResponseEntity.badRequest().body(response.errorAccumulators);
        }

        var idCreated = ((User) response.body).getId();

        return ResponseEntity.created(URI.create("/users/" + idCreated)).body(user);
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