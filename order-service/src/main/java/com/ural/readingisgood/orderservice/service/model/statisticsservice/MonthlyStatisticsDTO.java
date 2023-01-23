package com.ural.readingisgood.orderservice.service.model.statisticsservice;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyStatisticsDTO {


    private String monthName;
    private Integer countOfOrders;
    private double totalPrice;
    private Integer totalQuantity;
}
