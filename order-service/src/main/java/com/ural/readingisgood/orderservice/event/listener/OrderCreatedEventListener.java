package com.ural.readingisgood.orderservice.event.listener;

import com.ural.readingisgood.orderservice.event.OrderCreatedEvent;
import com.ural.readingisgood.orderservice.service.contract.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventListener implements
        ApplicationListener<OrderCreatedEvent> {

    @Autowired
    private InventoryService inventoryService;

    @Override
    public void onApplicationEvent(OrderCreatedEvent event) {

        inventoryService.confirmReservedInventory(event.getReservedInventoryId());

    }
}

