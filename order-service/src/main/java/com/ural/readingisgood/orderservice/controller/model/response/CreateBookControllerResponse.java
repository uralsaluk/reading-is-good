package com.ural.readingisgood.orderservice.controller.model.response;

import com.ural.readingisgood.orderservice.core.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBookControllerResponse extends BaseResponse {

    private Long id;

    private String name;

    private String author;

    private double price;

    private Integer stock;
}
