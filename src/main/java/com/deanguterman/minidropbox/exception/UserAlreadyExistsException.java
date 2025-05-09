package com.deanguterman.minidropbox.exception;

// Thrown if email or username is already taken
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
