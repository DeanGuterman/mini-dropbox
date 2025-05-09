package com.deanguterman.minidropbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Permit unauthenticated access to the registration and login endpoints
                                .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                                // Require authentication for all other requests
                                .anyRequest().authenticated()
                )
                // Disable CSRF protection for simplicity in this example, consider enabling for production
                // If you keep CSRF enabled, you'll need to handle CSRF tokens on the client side for POST requests
                .csrf(csrf -> csrf.disable());


        return http.build();
    }

    // You will likely need to define a PasswordEncoder bean here later
    // and potentially configure authentication (e.g., DaoAuthenticationProvider)
}