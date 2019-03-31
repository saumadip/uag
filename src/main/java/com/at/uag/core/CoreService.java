package com.at.uag.core;

import com.at.uag.memory.Action;
import com.at.uag.memory.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class CoreService extends AbstractService
{

    @Autowired
    private ServiceFactory serviceFactory;


    public CoreService(Queue<Event> eventsQueue)
    {
        super(eventsQueue);
    }

    @Override
    public void eventProcessor(Event e)
    {

        if(e.getAction().equals(Action.REMOVEUSER))
        {
            IService service = serviceFactory.getService(ServiceFactory.ServiceType.USERREMOVER);
            service.addEvent(e);
        }
        else if(e.getAction().equals(Action.REMOVEPERM))
        {
            IService service = serviceFactory.getService(ServiceFactory.ServiceType.PERMMISSIONREMOVER);
            service.addEvent(e);
        }

    }

    @Override
    public void addEvent(Event e)
    {
        eventsQueue.add(e);
        if(!eventsQueue.isEmpty())
        {
            semaphoreEmpty.release();
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            Event event = queueReader();
            eventProcessor(event);
        }

    }
}
