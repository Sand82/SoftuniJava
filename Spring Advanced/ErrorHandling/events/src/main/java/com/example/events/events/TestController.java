package com.example.events.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    private ApplicationEventPublisher applicationEventPublisher;

    public TestController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("/create-order")
    public String test() {

        OrderCreateEvent event = new OrderCreateEvent(this, UUID.randomUUID().toString());

        applicationEventPublisher.publishEvent(event);

        return "test";
    }
}
