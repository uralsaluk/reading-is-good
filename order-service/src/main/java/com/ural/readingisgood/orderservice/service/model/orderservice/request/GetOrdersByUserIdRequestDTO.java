package com.ural.readingisgood.orderservice.service.model.orderservice.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetOrdersByUserIdRequestDTO {

    private String userName;
    private int offset = 0;

    private int limit = 3;

}
