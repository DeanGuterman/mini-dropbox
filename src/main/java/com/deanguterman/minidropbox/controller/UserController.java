package com.deanguterman.minidropbox.controller;

import com.deanguterman.minidropbox.dto.UserLoginRequest;
import com.deanguterman.minidropbox.dto.UserRegistrationRequest;
import com.deanguterman.minidropbox.exception.UserAlreadyExistsException;
import com.deanguterman.minidropbox.exception.UserDoesntExistException;
import com.deanguterman.minidropbox.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest){
        try{
            userService.registerUser(userRegistrationRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest){
        try{
            String jwt = userService.loginUser(userLoginRequest);
            return ResponseEntity.ok(jwt);
        } catch (UserDoesntExistException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
