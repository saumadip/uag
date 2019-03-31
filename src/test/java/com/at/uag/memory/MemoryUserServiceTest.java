package com.at.uag.memory;

import com.at.uag.Exceptions.UserAlreadyExistsException;
import com.at.uag.Exceptions.UserNotFoundException;
import com.at.uag.api.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MemoryUserServiceTest
{

    private MemoryUserService memoryUserService;

    private List<User> userList;

    private User userOne;

    User userTwo;

    UUID toSearch;

    @Mock
    BlockingQueue<Event> eventBlockingQueue;

    @Before
    public void setup()
    {
        toSearch = UUID.randomUUID();

        userOne = new User("sam",toSearch);
        userTwo = new User("maz",UUID.randomUUID());

        userList =  new ArrayList<>();
        userList.addAll(Arrays.asList(userOne,userTwo));
        memoryUserService = new MemoryUserService(userList);
    }


    @Test(expected = UserAlreadyExistsException.class)
    public void createUserAccount_userAlreadyExists() throws UserAlreadyExistsException
    {
        memoryUserService.createUserAccount("sam");
        assertEquals(2,userList.size());
    }

    @Test
    public void createUserAccount_createUser() throws UserAlreadyExistsException
    {
        memoryUserService.createUserAccount("joy");
        assertEquals(3,userList.size());
    }


    @Test
    public void findUser() throws UserNotFoundException {
        memoryUserService = new MemoryUserService(userList);

        User user = memoryUserService.findUser(toSearch);

        assertEquals(user,userOne);
    }

    @Test(expected = UserNotFoundException.class)
    public void find_Unavailable_User() throws UserNotFoundException
    {
        memoryUserService = new MemoryUserService(userList);

        User user = memoryUserService.findUser(UUID.randomUUID());
    }

    @Test
    public void removeUserbyID()
    {

    }
}