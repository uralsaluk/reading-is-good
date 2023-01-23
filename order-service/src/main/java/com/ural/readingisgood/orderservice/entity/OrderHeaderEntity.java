package com.ural.readingisgood.orderservice.entity;


import com.ural.readingisgood.orderservice.entity.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_headers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHeaderEntity extends BaseDaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private double totalPrice;


    @Column(name = "PAYMENT_ID", nullable = false)
    private String paymentId;

    @Column(name = "RESERVED_STOCK_ID", nullable = false)
    private String reservedStockId;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLineEntity> items;


}
