package com.ural.readingisgood.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyStatisticsDAOModel implements Serializable {



//    @Value("#{target.NAME}")
@Column(name = "NAME")
    private String monthName;
 //   @Value("#{target.COUNTOFORDERS}")
 @Column(name = "COUNTOFORDERS")
    private Integer countOfOrders;
 //   @Value("#{target.TOTALPRICE}")
 @Column(name = "TOTALPRICE")
    private double totalPrice;
 //   @Value("#{target.TOTALQUANTITY}")
 @Column(name = "TOTALQUANTITY")
    private Integer totalQuantity;


}
