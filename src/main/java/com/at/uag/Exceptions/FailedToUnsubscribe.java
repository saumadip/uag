package com.at.uag.Exceptions;

import org.springframework.stereotype.Component;

public class FailedToUnsubscribe extends Exception
{
    public FailedToUnsubscribe(String message) {
        super(message);
    }

    public FailedToUnsubscribe(String message, Throwable cause) {
        super(message, cause);
    }
}
