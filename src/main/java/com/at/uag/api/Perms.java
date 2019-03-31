package com.at.uag.api;

import org.springframework.stereotype.Component;


public enum Perms
{
    READ(4),
    WRITE(2),
    DELETE(1);

    private int perms;

    private Perms(int val)
    {
        this.perms =val;
    }

}
