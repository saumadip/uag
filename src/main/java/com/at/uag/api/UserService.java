package com.at.uag.api;


import com.at.uag.Exceptions.UserAlreadyExistsException;
import com.at.uag.Exceptions.UserNotFoundException;
import com.at.uag.memory.MemoryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserService
{

    @Autowired
    private MemoryUserService memoryUserService;

    @RequestMapping(value = "/reguser", method= RequestMethod.POST)
    public User registerUser(@RequestParam(value = "username") String username) throws UserAlreadyExistsException
    {
        return memoryUserService.createUserAccount(username);
    }

    @RequestMapping(value = "/unregisteruser", method = RequestMethod.POST)
    public boolean unRegisterUser(@RequestParam(value= "userID")  UUID userID) throws UserNotFoundException
    {
        return memoryUserService.removeUserbyID(userID);
    }

}
