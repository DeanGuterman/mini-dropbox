package com.deanguterman.minidropbox.service;

import com.deanguterman.minidropbox.dto.UserLoginRequest;
import com.deanguterman.minidropbox.dto.UserRegistrationRequest;
import com.deanguterman.minidropbox.entity.User;
import com.deanguterman.minidropbox.exception.UserAlreadyExistsException;
import com.deanguterman.minidropbox.exception.UserDoesntExistException;
import com.deanguterman.minidropbox.repository.UserRepository;
import com.deanguterman.minidropbox.security.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

// Handles saving files to disk and DB, and returning them for download
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String registerUser(UserRegistrationRequest request) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(request.getEmail()) || userRepository.existsByUsername(request.getUsername())) throw new UserAlreadyExistsException("Email or Username already exists");
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(request.getEmail(), request.getUsername(), encodedPassword);
        userRepository.save(newUser);
        return "User registered successfully";
    }

    public String loginUser(UserLoginRequest request) throws UserDoesntExistException {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPasswordHash())){
                UserDetails userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPasswordHash())
                        .authorities(new ArrayList<>())
                        .build();

                return jwtService.generateToken(userDetails);
            }
        }
        throw new UserDoesntExistException("Invalid credentials.");
    }
}
