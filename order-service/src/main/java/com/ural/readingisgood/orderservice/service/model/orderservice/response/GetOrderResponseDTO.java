package com.ural.readingisgood.orderservice.service.model.orderservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponseDTO {


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
