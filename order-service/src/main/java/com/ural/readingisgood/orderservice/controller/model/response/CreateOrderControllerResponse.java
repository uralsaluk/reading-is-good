package com.ural.readingisgood.orderservice.controller.model.response;

import com.ural.readingisgood.orderservice.controller.model.OrderLineControllerDTO;
import com.ural.readingisgood.orderservice.core.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderControllerResponse extends BaseResponse {


    private Long id;

    private String userName;

    private String email;

    private double totalPrice;

    private String status;


    private String paymentId;

    private String reservedStockId;


    private List<OrderLineControllerDTO> items;
}
