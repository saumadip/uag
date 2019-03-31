package com.at.uag.Exceptions;

import org.springframework.stereotype.Component;


public class GroupNotFoundException extends Exception
{
    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
