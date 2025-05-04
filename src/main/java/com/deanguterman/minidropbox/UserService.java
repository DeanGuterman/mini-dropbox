package com.deanguterman.minidropbox;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(UserRegistrationRequest request) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(request.getEmail()) || userRepository.existsByUsername(request.getUsername())) throw new UserAlreadyExistsException("Email or Username already exists");
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(request.getEmail(), request.getUsername(), encodedPassword);
        userRepository.save(newUser);
        return "User registered successfully";
    }
}
