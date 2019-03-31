package com.at.uag.configuration;

import com.at.uag.api.Group;
import com.at.uag.api.User;
import com.at.uag.core.CoreService;
import com.at.uag.core.IService;
import com.at.uag.core.ServiceFactory;
import com.at.uag.core.UserRemoverService;
import com.at.uag.memory.Event;
import com.at.uag.memory.MemoryGroupService;
import com.at.uag.memory.MemoryMembershipService;
import com.at.uag.memory.MemoryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class ServiceConfiguration
{

    @Bean
    @DependsOn({"getuserList"})
    public MemoryUserService memoryUserService(List<User> userList)
    {

        return new MemoryUserService(userList);
    }

    @Bean
    @DependsOn({"memoryGroupService","memoryUserService"})
    public MemoryMembershipService memoryMembershipService()
    {
        return new MemoryMembershipService();
    }

    @Bean
    @DependsOn({"getGroupList"})
    public MemoryGroupService memoryGroupService(List<Group> groupList)
    {

        return new MemoryGroupService(groupList);
    }

    @Bean
    public List<Group> getGroupList()
    {
        return Collections.synchronizedList(new ArrayList<>());

    }

    @Bean
    public List<User> getuserList()
    {
        return Collections.synchronizedList(new ArrayList<>());

    }

    @Bean
    public IService coreService()
    {
        CoreService coreService = new CoreService(new LinkedBlockingQueue<>());
        Thread t1 = new Thread(coreService);
        t1.setName("coreService");
        t1.start();

        return coreService;
    }

    @Bean
    public ServiceFactory serviceFactory()
    {
        return new ServiceFactory(new HashMap<>());
    }

    @Bean
    public UserRemoverService userRemoverService()
    {

        UserRemoverService userRemoverService = new UserRemoverService(new LinkedBlockingQueue<>());

        Thread t1 = new Thread(userRemoverService);
        t1.setName("userRemoverService");
        t1.start();
        return userRemoverService;
    }

}
