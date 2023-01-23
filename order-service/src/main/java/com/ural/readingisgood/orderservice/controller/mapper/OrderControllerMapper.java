package com.ural.readingisgood.orderservice.controller.mapper;

import com.ural.readingisgood.orderservice.controller.model.request.CreateOrderControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.response.CreateOrderControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderByQueryControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderControllerResponse;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.CreateOrderRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.CreateOrderResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderByQueryResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class OrderControllerMapper {

    public static final OrderControllerMapper INSTANCE = Mappers.getMapper(OrderControllerMapper.class);


    public abstract CreateOrderControllerResponse toCreateOrderControllerResponse(CreateOrderResponseDTO responseDTO);

    public abstract CreateOrderRequestDTO toCreateOrderRequestDTO(CreateOrderControllerRequest controllerRequest);

    public abstract GetOrderControllerResponse toGetOrderControllerResponse(GetOrderResponseDTO getOrderResponseDTO);

    public abstract GetOrderByQueryControllerResponse toGetOrderByQueryControllerResponse(GetOrderByQueryResponseDTO getOrderByQueryResponseDTO);
}
