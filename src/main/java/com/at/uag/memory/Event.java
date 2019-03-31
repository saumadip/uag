package com.at.uag.memory;

import com.at.uag.api.User;


public class Event
{
    private User user;

    private  Action action;

    public Event(){}

    public Event(User user, Action action) {
        this.user = user;
        this.action = action;
    }


    public User getUser() {
        return user;
    }

    public Action getAction() {
        return action;
    }
}
