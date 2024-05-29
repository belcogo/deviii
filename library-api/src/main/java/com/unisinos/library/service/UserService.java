package com.unisinos.library.service;

import com.unisinos.library.model.Role;
import com.unisinos.library.model.User;
import com.unisinos.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder pwdEncoder;


    public boolean registerUser(User user) {
        Optional<User> userContainer = userRepository.findByEmail(user.getEmail());

        if(userContainer.isEmpty()) {
            user.setPassword(pwdEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }

        return false;
    }

}
