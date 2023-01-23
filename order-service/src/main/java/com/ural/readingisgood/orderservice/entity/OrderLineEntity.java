package com.ural.readingisgood.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ural.readingisgood.orderservice.entity.constant.LineStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_lines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineEntity extends BaseDaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LineStatus status;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @JoinColumn(name = "ORDER_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private OrderHeaderEntity order;
}
