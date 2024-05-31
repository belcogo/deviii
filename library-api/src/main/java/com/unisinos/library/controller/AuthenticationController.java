package com.unisinos.library.controller;

import com.unisinos.library.dto.request.LoginRequest;
import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.dto.response.LoginResponse;
import com.unisinos.library.dto.response.UserDetailsResponse;
import com.unisinos.library.model.User;
import com.unisinos.library.service.AuthenticationService;
import com.unisinos.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        try {
            var user = authenticationService.loadUserByUsername(request.email);

            if (!authenticationService.isPasswordValid(user.getPassword(), request.password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            var responseBody = LoginResponse
                    .builder()
                    .token(getAuthToken(request.email, request.password))
                    .build();

            return ResponseEntity.ok(responseBody);
        } catch (UsernameNotFoundException u) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/auth/user-details")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String authorization) {
        String username = getUsername(authorization);
        var user = userService.getUserByEmail(username);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var responseBody =
                UserDetailsResponse.builder()
                    .email(user.get().getEmail())
                    .name(user.get().getName())
                    .score(1)
                    .id(user.get().getId())
                    .build();

        return ResponseEntity.ok(responseBody);
    }

    private String getUsername(String authorizationToken) {
        if (authorizationToken != null && authorizationToken.toLowerCase().startsWith("basic")) {
            String base64Credentials = authorizationToken.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            return credentials.split(":", 2)[0];
        }

        return null;
    }

    private String getAuthToken(String email, String senha) {
        String credentials = email + ":" + senha;
        byte[] tokenByte = Base64.getEncoder().encode(credentials.getBytes());
        return new String(tokenByte, StandardCharsets.UTF_8);
    }
}
