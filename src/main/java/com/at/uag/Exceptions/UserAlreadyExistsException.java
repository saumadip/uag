package com.at.uag.Exceptions;

import org.springframework.stereotype.Component;


public class UserAlreadyExistsException extends Exception
{
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
