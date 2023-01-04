package com.example.events.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductQuantityCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductQuantityCalculator.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreate(OrderCreateEvent orderCreateEvent) {
        LOGGER.info("Order no {} has bean created. I,m going to calculate the current products quantities.", orderCreateEvent.getOrderId());

    }

}
