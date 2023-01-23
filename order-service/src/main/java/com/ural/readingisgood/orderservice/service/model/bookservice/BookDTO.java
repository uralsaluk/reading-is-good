package com.ural.readingisgood.orderservice.service.model.bookservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class BookDTO {

    private Long id;

    private String name;

    private String author;

    private double price;

    private Integer stock;
}
