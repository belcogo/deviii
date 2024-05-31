package com.unisinos.library.service;

import com.unisinos.library.model.User;
import com.unisinos.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Set<GrantedAuthority> set = new HashSet<>();


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not exists by Username");
        }

        GrantedAuthority authorities = new SimpleGrantedAuthority(user.get().getRole().toString());
        set.add(authorities);

        return new org.springframework.security.core.userdetails.User(email, user.get().getPassword(), set);
    }

    public boolean isPasswordValid(String correctPassword, String informedPassword) {
        return passwordEncoder.matches(informedPassword, correctPassword);
    }

    public String getDecryptPassword(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }
}
