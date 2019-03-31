package com.at.uag.core;

import com.at.uag.memory.Event;

public interface IService
{
    void eventProcessor(Event e);

    void addEvent(Event e);
}
