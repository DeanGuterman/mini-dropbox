package com.deanguterman.minidropbox;

// Custom exception for handling pre-existing emails and password on registration
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
