package com.deanguterman.minidropbox.dto;


import jakarta.validation.constraints.NotBlank;

// User login DTO
public class UserLoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    // Getters
    public String getUsername(){return username;}
    public String getPassword(){return password;}

    // Setters
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
}
