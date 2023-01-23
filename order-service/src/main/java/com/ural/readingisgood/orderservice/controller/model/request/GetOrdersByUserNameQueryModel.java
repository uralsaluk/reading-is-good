package com.ural.readingisgood.orderservice.controller.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class GetOrdersByUserNameQueryModel {

    @NotEmpty(message = "Username is required field")
    private String userName;

    private int offset=0;

    private int limit=3;
}
