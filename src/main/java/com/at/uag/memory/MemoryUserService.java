package com.at.uag.memory;

import com.at.uag.Exceptions.UserAlreadyExistsException;
import com.at.uag.Exceptions.UserNotFoundException;
import com.at.uag.api.User;
import com.at.uag.core.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Scope("singleton")
public class MemoryUserService
{

    private  List<User> users;

    @Autowired
    private IService coreService;


    public MemoryUserService(List<User> users)
    {
        this.users = users;
    }

    /**
     * Add new user
     * @param name
     * @return
     * @throws UserAlreadyExistsException
     */
    public User createUserAccount(String name) throws UserAlreadyExistsException
    {
        if(!StringUtils.isEmpty(name) && users.parallelStream().noneMatch(usr->usr.getUserName().equalsIgnoreCase(name)))
        {
            User newUser = new User(name, UUID.randomUUID());
            users.add(newUser);
            return newUser;
        }
        throw new UserAlreadyExistsException("User Already Exists");
    }

    /**
     * find User
     * @param usrID
     * @return
     * @throws UserNotFoundException
     */
    public User findUser(UUID usrID) throws UserNotFoundException
    {
        Optional<User> usrSearch = users.stream().filter(usr -> usr.getUserID().equals(usrID)).findAny();

        if(!usrSearch.isPresent())
            throw new UserNotFoundException("User not found");

        return usrSearch.get();
    }

    /**
     * Remove user
     * @param userID
     * @return
     */
    public boolean removeUserbyID(UUID userID) throws UserNotFoundException
    {
        Optional<User> userToRemove = users.stream().filter(usr -> usr.getUserID().equals(userID)).findAny();

        if(userToRemove.isPresent())
        {
            Event removeUserEvent = new Event(userToRemove.get(),Action.REMOVEUSER);
            coreService.addEvent(removeUserEvent);
            return true;
        }
        throw new UserNotFoundException("User not found");
    }

}
