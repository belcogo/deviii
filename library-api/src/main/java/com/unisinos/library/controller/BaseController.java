package com.unisinos.library.controller;

import com.unisinos.library.model.User;
import com.unisinos.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

public abstract class BaseController {
    @Autowired
    private UserService userService;

    protected Optional<User> getUserByAuthToken(String authorizationToken) {
        String username = getUsername(authorizationToken);
        return userService.getUserByEmail(username);
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
