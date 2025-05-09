package com.deanguterman.minidropbox.exception;

// Thrown if login credentials are incorrect
public class UserDoesntExistException extends RuntimeException{
    public UserDoesntExistException(String message){
        super(message);
    }
}
