package com.ural.readingisgood.orderservice.controller.mapper;

import com.ural.readingisgood.orderservice.controller.model.request.CreateBookControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.request.UpdateInventoryControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.response.CreateBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetBookControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.UpdateInventoryControllerResponse;
import com.ural.readingisgood.orderservice.service.model.bookservice.BookDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.request.UpdateBookInventoryRequestDTO;
import com.ural.readingisgood.orderservice.service.model.bookservice.response.UpdateBookInventoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class BookControllerMapper {

    public static final BookControllerMapper INSTANCE = Mappers.getMapper(BookControllerMapper.class);

    public abstract BookDTO toBookDTO(CreateBookControllerRequest request);

    public abstract CreateBookControllerResponse toCreateBookControllerResponse(BookDTO bookDTO);

    public abstract GetBookControllerResponse toGetBookControllerResponse(BookDTO bookDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "request.stock", target = "stock")
    public abstract UpdateBookInventoryRequestDTO toUpdateBookInventoryRequestDTO(Long id,
                                                                                  UpdateInventoryControllerRequest request);

    public abstract UpdateInventoryControllerResponse toUpdateInventoryControllerResponse(UpdateBookInventoryResponseDTO request);
}
