package com.ural.readingisgood.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "reserved_inventory")
public class ReservedInventory {

    @Id
    @Field("_id")
    private String id;

    private Boolean confirmed = false;

    private List<ReservedProduct> productList;

}
