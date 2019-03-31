package com.at.uag.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * User class
 */
public class User
{
    private String userName;

    private  UUID userID;

    public User(){}

    public User(String userName, UUID userID)
    {
        this.userName = userName;
        this.userID = userID;
    }

    public String getUserName()
    {
        return userName;
    }

    public UUID getUserID()
    {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getUserID(), user.getUserID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getUserID());
    }
}
