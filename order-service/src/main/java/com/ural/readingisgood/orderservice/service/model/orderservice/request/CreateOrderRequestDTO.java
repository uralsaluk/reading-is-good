package com.ural.readingisgood.orderservice.service.model.orderservice.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrderRequestDTO {

    private String mail;
    private String phone;
    private List<OrderedProductDTO> orderedProductList;


}
