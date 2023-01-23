package com.ural.readingisgood.orderservice.service.contract;

import com.ural.readingisgood.orderservice.entity.ReservedInventory;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.OrderedProductDTO;

import java.util.Set;

public interface InventoryService {


    ReservedInventory reserveInventory(Set<OrderedProductDTO> orderedProductList);


    void confirmReservedInventory(String reservedInventoryId);

    public void rollBackInventories(String reservedInventoryId);


}
