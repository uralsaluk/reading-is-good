package com.ural.readingisgood.orderservice.controller.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateInventoryControllerRequest {

    private Integer stock;
}
