package com.at.uag.core;

import com.at.uag.memory.Event;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public abstract class AbstractService implements Runnable,IService
{

    protected final Queue<Event> eventsQueue;

    protected Semaphore semaphoreEmpty = new Semaphore(0);

    protected AbstractService(Queue<Event> eventsQueue)
    {
        this.eventsQueue = eventsQueue;
    }

    public abstract void eventProcessor(Event e);

    @Override
    public abstract void run();

    protected Event queueReader()
    {

        try {
            semaphoreEmpty.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return eventsQueue.poll();

    }

}
