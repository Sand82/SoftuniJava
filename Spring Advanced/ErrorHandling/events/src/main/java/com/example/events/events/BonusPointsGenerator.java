package com.example.events.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonusPointsGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BonusPointsGenerator.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreate(OrderCreateEvent orderCreateEvent) {
        LOGGER.info("Order no {} has bean created. I,m going to gave bonus points to the client", orderCreateEvent.getOrderId());

    }
}
