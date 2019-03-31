package com.at.uag.Exceptions;

import org.springframework.stereotype.Component;


public class GroupCreationException extends Exception
{
    public GroupCreationException(String message) {
        super(message);
    }

    public GroupCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
