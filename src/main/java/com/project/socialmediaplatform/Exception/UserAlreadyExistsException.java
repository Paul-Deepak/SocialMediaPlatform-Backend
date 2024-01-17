package com.project.socialmediaplatform.Exception;

public class UserAlreadyExistsException extends RuntimeException{
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
