package com.ural.readingisgood.orderservice.service.model.bookservice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateBookInventoryResponseDTO {

    private Long id;

    private Integer stock;
}
