package com.at.uag.api;

import com.at.uag.Exceptions.FailedToSubscribe;
import com.at.uag.Exceptions.GroupNotFoundException;
import com.at.uag.Exceptions.UserNotFoundException;
import com.at.uag.memory.MemoryGroupService;
import com.at.uag.memory.MemoryMembershipService;
import com.at.uag.memory.MemoryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MembershipService
{

    @Autowired
    private MemoryMembershipService memoryMembershipService;

    @Autowired
    private MemoryUserService memoryUserService;

    @Autowired
    private MemoryGroupService memoryGroupService;


    @RequestMapping(method = RequestMethod.POST,value = "/subscribe")
    public UUID subscribe(@RequestParam(value = "userID") UUID userID,
                     @RequestParam(value = "groupID") UUID groupID,
                     @RequestParam(value = "perm")  Perms perm) throws FailedToSubscribe
    {

        try
        {
            Group group = memoryGroupService.findGroup(groupID);
            User user = memoryUserService.findUser(userID);

            memoryMembershipService.subscribe(user,perm,group);
        }
        catch (GroupNotFoundException | UserNotFoundException e)
        {
            throw new FailedToSubscribe("Could not create subscription",e);
        }

       //Todo: Create membershipTable and return id
        return UUID.randomUUID();
    }

}
