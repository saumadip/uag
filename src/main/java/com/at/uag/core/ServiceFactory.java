package com.at.uag.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceFactory
{

    @Autowired
    UserRemoverService userRemoverService;


    PermissionRemoverService permissionRemoverService;


    private final Map<ServiceType, IService> serviceMap;

    public ServiceFactory(Map<ServiceType, IService> serviceMap)
    {
        this.serviceMap = serviceMap;
    }


    public IService getService(ServiceType serviceType)
    {

        IService iService = serviceMap.get(serviceType);

        if(iService == null)
        {
            if(serviceType.equals(ServiceType.PERMMISSIONREMOVER))
            {
                iService = permissionRemoverService;
            }
            else if(serviceType.equals(ServiceType.USERREMOVER ))
            {
                iService = userRemoverService;
            }
        }

        serviceMap.put(serviceType,iService);
        return iService;
    }

    public enum ServiceType
    {
        USERREMOVER,PERMMISSIONREMOVER
    }
}
