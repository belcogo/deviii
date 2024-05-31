package com.unisinos.library.controller;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.dto.response.LoginResponse;
import com.unisinos.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestHeader("Authorization") String authorization) {
        String username = getUsername(authorization);
        var user = userService.getUserByEmail(username);

        if (user.isEmpty()) {
            var responseBody = ErrorMessageResponse.builder()
                    .message("User with this e-mail does not exists")
                    .errorCode("USER_002");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }

        var responseBody =
                LoginResponse.builder()
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
}
