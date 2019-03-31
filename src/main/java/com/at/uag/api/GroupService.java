package com.at.uag.api;

import com.at.uag.Exceptions.GroupCreationException;
import com.at.uag.Exceptions.GroupNotFoundException;
import com.at.uag.memory.MemoryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GroupService
{

    @Autowired
    private MemoryGroupService memoryGroupService;

    @RequestMapping(value = "/createGroup",method = RequestMethod.POST)
    public Group createGroup(@RequestParam("groupName") String name) throws GroupCreationException
    {
        return memoryGroupService.createGroup(name);
    }

    @RequestMapping(value = "/removeGroup",method = RequestMethod.POST)
    public boolean removeGroup(@RequestParam("groupId")UUID groupID) throws GroupNotFoundException {

        memoryGroupService.removeGroup(groupID);
        return true;
    }


}
