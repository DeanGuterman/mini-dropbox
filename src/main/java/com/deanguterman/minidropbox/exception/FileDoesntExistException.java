package com.deanguterman.minidropbox.exception;

public class FileDoesntExistException extends RuntimeException{
    public FileDoesntExistException(String message) { super(message); }
}
