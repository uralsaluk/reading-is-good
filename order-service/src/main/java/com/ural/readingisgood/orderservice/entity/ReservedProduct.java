package com.ural.readingisgood.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservedProduct {

    private Long bookId;

    private Integer quantity;
}
