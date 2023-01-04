package com.example.events.events;

import org.springframework.context.ApplicationEvent;

public class OrderCreateEvent extends ApplicationEvent {
    private String orderId;

    public OrderCreateEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
