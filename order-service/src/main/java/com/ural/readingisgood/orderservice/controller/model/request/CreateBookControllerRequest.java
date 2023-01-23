package com.ural.readingisgood.orderservice.controller.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Data
public class CreateBookControllerRequest {


    private String name;

    private String author;


    @Min(value = 0, message = "Price should be greater than 0")
    private double price;

    @Min(value = 0, message = "Stock should be greater than 0")
    private Integer stock;

}
