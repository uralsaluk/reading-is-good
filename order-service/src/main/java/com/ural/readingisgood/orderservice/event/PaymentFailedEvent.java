package com.ural.readingisgood.orderservice.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

public class PaymentFailedEvent extends ApplicationEvent {
    private String reservedInventoryId;

    private SecurityContextHolderStrategy securityContextHolderStrategy;

    public PaymentFailedEvent(SecurityContextHolderStrategy securityContextHolderStrategy, String reservedInventoryId) {
        super(securityContextHolderStrategy);
        this.reservedInventoryId = reservedInventoryId;
        this.securityContextHolderStrategy = securityContextHolderStrategy;
    }

    public String getReservedInventoryId() {
        return reservedInventoryId;
    }

    public SecurityContextHolderStrategy getSecurityContextHolderStrategy() {
        return securityContextHolderStrategy;
    }
}