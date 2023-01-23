package com.ural.readingisgood.orderservice.controller.mapper;

import com.ural.readingisgood.orderservice.controller.model.request.GetOrdersByUserNameQueryModel;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderByUserNameControllerResponse;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.GetOrdersByUserIdRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrdersByUserIdResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CustomerControllerMapper {

    public static final CustomerControllerMapper INSTANCE = Mappers.getMapper(CustomerControllerMapper.class);

    public abstract GetOrdersByUserIdRequestDTO toGetOrdersByUserIdRequestDTO(GetOrdersByUserNameQueryModel controllerRequest);

    public abstract GetOrderByUserNameControllerResponse toGetOrderByUserNameControllerResponse(GetOrdersByUserIdResponseDTO getOrderResponseDTO);


}
