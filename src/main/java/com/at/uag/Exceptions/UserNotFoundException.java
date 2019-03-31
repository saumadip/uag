package com.at.uag.Exceptions;

import org.springframework.stereotype.Component;


public class UserNotFoundException extends Exception
{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
