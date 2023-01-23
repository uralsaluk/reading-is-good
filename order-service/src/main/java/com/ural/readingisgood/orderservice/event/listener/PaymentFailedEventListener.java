package com.ural.readingisgood.orderservice.event.listener;

import com.ural.readingisgood.orderservice.event.PaymentFailedEvent;
import com.ural.readingisgood.orderservice.service.contract.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentFailedEventListener implements
        ApplicationListener<PaymentFailedEvent> {

    @Autowired
    private InventoryService inventoryService;

    @Override
    public void onApplicationEvent(PaymentFailedEvent event) {

        // ContextUtil.getContextStrategy().setContext(event.getSecurityContextHolderStrategy().getContext());

        inventoryService.rollBackInventories(event.getReservedInventoryId());

    }
}

