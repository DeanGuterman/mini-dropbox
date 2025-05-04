package com.deanguterman.minidropbox;

// Custom exception for handling login attempts with non-existing username and password combinations
public class UserDoesntExistException extends RuntimeException{
    public UserDoesntExistException(String message){
        super(message);
    }
}
