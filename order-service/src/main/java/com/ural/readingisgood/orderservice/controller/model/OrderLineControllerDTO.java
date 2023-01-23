package com.ural.readingisgood.orderservice.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderLineControllerDTO {

    private Long id;

    private Long bookId;

    private String bookName;

    private String status;

    private double price;

    private Integer quantity;


}
