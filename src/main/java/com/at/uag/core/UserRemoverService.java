package com.at.uag.core;

import com.at.uag.Exceptions.GroupNotFoundException;
import com.at.uag.api.Group;
import com.at.uag.api.Perms;
import com.at.uag.api.User;
import com.at.uag.memory.Event;
import com.at.uag.memory.MemoryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class UserRemoverService extends AbstractService
{

    @Autowired
    MemoryGroupService memoryGroupService;


    public UserRemoverService(Queue<Event> eventsQueue)
    {
        super(eventsQueue);
    }

    @Override
    public void eventProcessor(Event e)
    {
        User user = e.getUser();

        //Remove from usersLIst
        List<Group> groupList = memoryGroupService.getGroupList();
        groupList.forEach(group -> group.getUsersInGroup().removeIf(usr->usr.equals(user)));
        //Remove Perm aswell
        groupList.stream().map(Group::getPermMap).forEach(permsListMap ->
        {
            for(Map.Entry<Perms, List<User>> tempEntry :permsListMap.entrySet())
            {
                tempEntry.getValue().removeIf(usr->usr.equals(user));
            }

        });
    }

    @Override
    public void addEvent(Event e)
    {
        eventsQueue.add(e);
        semaphoreEmpty.release();
    }

    @Override
    public void run()
    {
        while (true)
        {
            eventProcessor(queueReader());
        }
    }
}
