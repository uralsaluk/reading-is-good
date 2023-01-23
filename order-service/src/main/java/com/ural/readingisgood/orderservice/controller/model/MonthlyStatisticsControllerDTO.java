package com.ural.readingisgood.orderservice.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyStatisticsControllerDTO {


    private String monthName;
    private Integer countOfOrders;
    private double totalPrice;
    private Integer totalQuantity;
}
