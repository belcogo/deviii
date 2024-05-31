package com.unisinos.library.service;

import com.unisinos.library.model.Role;
import com.unisinos.library.model.User;
import com.unisinos.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder pwdEncoder;


    public Optional<User> registerUser(User user) {
        Optional<User> userContainer = userRepository.findByEmail(user.getEmail());

        if (userContainer.isEmpty()) {
            user.setPassword(pwdEncoder.encode(user.getPassword()));
            user = userRepository.save(user);
            return Optional.of(user);
        }

        return Optional.empty();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
