package com.bcfl.registration.authentication;

public class UserExistException extends Exception {
    public UserExistException(String message) {
        super(message);
    }
}
