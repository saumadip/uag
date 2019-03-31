package com.at.uag.core;

import com.at.uag.memory.Action;
import com.at.uag.memory.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CoreServiceTest
{
    @Mock
    ServiceFactory serviceFactory;

    @InjectMocks
    CoreService coreService;

    @Mock
    Event event;


    @Mock
    IService iService;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void eventProcessor()
    {
        when(serviceFactory.getService(ServiceFactory.ServiceType.USERREMOVER)).thenReturn(iService);
        when(event.getAction()).thenReturn(Action.REMOVEUSER);
        doAnswer((Answer) in -> {

            Event argument = in.getArgument(0);

            assertEquals(Action.REMOVEUSER,argument.getAction());

            return "";

        }).when(iService).addEvent(event);

        coreService.eventProcessor(event);

        verify(iService,times(1)).addEvent(event);
    }

    @Test
    public void addEvent() {
    }

    @Test
    public void run() {
    }
}