package com.at.uag.memory;

import com.at.uag.Exceptions.FailedToSubscribe;
import com.at.uag.Exceptions.FailedToUnsubscribe;
import com.at.uag.Exceptions.GroupNotFoundException;
import com.at.uag.api.Group;
import com.at.uag.api.Perms;
import com.at.uag.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MemoryMembershipService
{




    /**
     * Adds user to group with the specified permissions
     * @param user
     * @param perm
     * @return
     */
    public User subscribe(User user, Perms perm, Group group) throws FailedToSubscribe {

        if(user == null || perm == null || group == null)
            throw new InvalidParameterException("Param cannot be null");


        if(group.getUsersInGroup().contains(user))
        {
            if(group.getPermMap().get(perm).contains(user))
                return user;
            else
            {
                List<User> users = group.getPermMap().get(perm);

                if(users != null)
                    users.add(user);
                else
                {

                    List<User> usersNew = new ArrayList<>();
                    usersNew.add(user);
                    group.getPermMap().put(perm, usersNew);
                }

                return user;
            }
        }
        else
        {
            group.getUsersInGroup().add(user);


            List<User> users = group.getPermMap().get(perm);

            if(users != null)
                users.add(user);
            else
            {

                List<User> usersNew = new ArrayList<>();
                usersNew.add(user);
                group.getPermMap().put(perm, usersNew);
            }

            return user;
        }
    }

    /**
     * Adds user to group with only readPermissions
     * @param user
     * @return
     */
    public User subscribe(User user,Group group) throws FailedToSubscribe {

        if(user == null)
            throw new InvalidParameterException("Param cannot be null");

        return subscribe(user,Perms.READ,group);
    }


    /**
     * Removes user permission within the group.
     * If user is the last user then removes the permission and also removes the user from the group
     * @param user
     * @param perm
     * @return true
     * @throws InvalidKeyException
     */
    public boolean unsubscribe(User user,Perms perm, Group group) throws InvalidKeyException, FailedToUnsubscribe {
        if(user == null || perm == null)
            throw new InvalidParameterException("Param cannot be null");


        if(group.getUsersInGroup().contains(user))
        {
            if(group.getPermMap().get(perm).contains(user))
            {
                group.getPermMap().get(perm).remove(user);

                //Todo:can be abstracted out to GroupService
                if(canUserbeRemovedFromGroup(user,group))
                {
                    group.getUsersInGroup().remove(user);
                }
                return true;
            }
            else
            {
                //Todo: create valid exceptions
                throw new InvalidKeyException("User doesn't have permission");
            }
        }
        else
        {
            throw new InvalidKeyException("User Not Found in Group");
        }

    }

    /**
     * check if we can remove the user from the group
     * @param user
     * @return
     */
    private boolean canUserbeRemovedFromGroup(User user, Group group)
    {
        for(Map.Entry<Perms, List<User>> allMapEntry : group.getPermMap().entrySet())
        {
            if(allMapEntry.getValue().contains(user))
            {
                return false;
            }
        }
        return true;
    }
}
