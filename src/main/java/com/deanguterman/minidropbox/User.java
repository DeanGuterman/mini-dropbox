package com.deanguterman.minidropbox;

import jakarta.persistence.*;

@Entity
public class User {

    // Auto generate unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Every user needs to have a unique email, username and password
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String passwordHash;

    // No-arg constructor for JPA
    public User(){}

    // All-arg constructor
    public User(String email, String username, String passwordHash){
        this.email = email;
        this. username = username;
        this. passwordHash = passwordHash;
    }

    // Getters
    public Long getId(){return id;}
    public String getEmail(){return email;}
    public String getUsername(){return username;}
    public String getPasswordHash(){return passwordHash;}

    // Setters
    public void setEmail(String email){this.email = email;}
    public void setUsername(String username){this.username = username;}
    public void setPasswordHash(String passwordHash){this.passwordHash = passwordHash;}

}
