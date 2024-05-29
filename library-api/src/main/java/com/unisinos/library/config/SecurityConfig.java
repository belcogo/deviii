package com.unisinos.library.config;

import com.unisinos.library.security.UnauthorizedEntrypoint;
import com.unisinos.library.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UnauthorizedEntrypoint unauthorizedEntrypoint;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {

        http.csrf(htpSecurity -> htpSecurity.disable());

        http.securityMatcher("/users","/index")
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/users").permitAll()
                                .anyRequest().authenticated())

                .httpBasic(httpSec -> httpSec.authenticationEntryPoint(unauthorizedEntrypoint)).userDetailsService(authenticationService);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

}
