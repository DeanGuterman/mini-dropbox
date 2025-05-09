package com.deanguterman.minidropbox.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// User Registration DTO
public class UserRegistrationRequest {

    // Only allow non-blank usernames with a length of between 3 and 20 characters
    @NotBlank
    @Size(min=3, max=20)
    private String username;

    // Only allow non-blank email addresses with a maximum length of 100 characters
    @NotBlank
    @Email
    @Size(max=100)
    private String email;

    // Only allow non-blank passwords with a length of between 8 and 64 characters
    @NotBlank
    @Size(min=8, max=64)
    private String password;


    // Getters
    public String getEmail(){return email;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}

    // Setters
    public void setEmail(String email){this.email = email;}
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}


}
