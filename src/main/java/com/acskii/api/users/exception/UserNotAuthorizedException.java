package com.acskii.api.users.exception;

/*
    return this exception if an authorized user attempted to perform an unauthorized action
    e.g. get a transaction not relating to their own User
*/

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
