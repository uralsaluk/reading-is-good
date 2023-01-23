package com.ural.readingisgood.orderservice.service.model.bookservice.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBookInventoryRequestDTO {

    private Long id;
    private Integer stock;
}
