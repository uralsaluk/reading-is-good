package com.ural.readingisgood.orderservice.service.mapper;

import com.ural.readingisgood.orderservice.entity.BookEntity;
import com.ural.readingisgood.orderservice.entity.InventoryEntity;
import com.ural.readingisgood.orderservice.service.model.bookservice.BookDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.response.UpdateBookInventoryResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class BookServiceMapper {


    public static final BookServiceMapper INSTANCE = Mappers.getMapper(BookServiceMapper.class);


    @Mapping(source = "request.stock", target = "inventory.stock")
    public abstract BookEntity toBookEntity(BookDTO request);

    @Mapping(source = "inventory.stock", target = "stock")
    public abstract BookDTO toBookDTO(BookEntity request);

    public abstract UpdateBookInventoryResponseDTO toUpdateBookInventoryResponseDTO(InventoryEntity request);


    @AfterMapping
    protected void setInventoryRelation(@MappingTarget BookEntity entity) {
        if (entity != null & entity.getInventory() != null) {
            entity.getInventory().setBook(entity);

        }

    }
}
