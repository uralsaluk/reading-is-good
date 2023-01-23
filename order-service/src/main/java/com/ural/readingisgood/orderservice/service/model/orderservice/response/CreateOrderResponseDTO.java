package com.ural.readingisgood.orderservice.service.model.orderservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderResponseDTO {


    private Long id;

    private String userName;

    private String email;

    private double totalPrice;

    private String status;

    private Date createDate;


    private String paymentId;

    private String reservedStockId;

    private List<OrderLineResponseDTO> items;
}
