package com.example.events.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component                           //implements ApplicationListener
public class ApplicationListenerTest  {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListenerTest.class);
    @EventListener(ServletRequestHandledEvent.class)
    public void onApplicationEvent(ApplicationEvent event) {
        LOGGER.info("I have received an Event: {}", event);
    }
}
