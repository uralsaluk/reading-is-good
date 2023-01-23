package com.ural.readingisgood.orderservice.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderControllerDTO  {


    private Long id;

    private String userName;

    private String email;

    private double totalPrice;

    private String status;

    private Date  createDate;


    private List<OrderLineControllerDTO> items;
}
