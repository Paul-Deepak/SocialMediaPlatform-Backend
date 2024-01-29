package com.project.socialmediaplatform.Exception;

public class FriendRequestAlreadySentException extends RuntimeException{
    public FriendRequestAlreadySentException(String message) {
        super(message);
    }
}
