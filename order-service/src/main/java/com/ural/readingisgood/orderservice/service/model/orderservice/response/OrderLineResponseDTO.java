package com.ural.readingisgood.orderservice.service.model.orderservice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderLineResponseDTO {

    private Long id;

    private Long bookId;

    private String bookName;

    private String status;

    private double price;

    private Integer quantity;


}
