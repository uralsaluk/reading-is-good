package com.ural.readingisgood.orderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "inventories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity extends BaseDaoModel {

    @Id
    @Column(name = "book_id")
    private Long id;

    @Column(name = "stock")
    private Integer stock;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "book_id")
    private BookEntity book;


}
