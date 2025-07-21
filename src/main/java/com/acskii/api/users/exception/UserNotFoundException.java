package com.acskii.api.users.exception;

/*
    Raise this exception if a user search does not return a User response.
*/
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
