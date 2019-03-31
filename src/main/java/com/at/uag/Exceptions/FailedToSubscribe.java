package com.at.uag.Exceptions;

import org.springframework.stereotype.Component;

public class FailedToSubscribe extends Exception
{
    public FailedToSubscribe(String message) {
        super(message);
    }

    public FailedToSubscribe(String message, Throwable cause) {
        super(message, cause);
    }
}
