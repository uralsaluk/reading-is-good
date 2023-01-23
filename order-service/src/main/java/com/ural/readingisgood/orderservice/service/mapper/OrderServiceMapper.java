package com.ural.readingisgood.orderservice.service.mapper;


import com.ural.readingisgood.orderservice.entity.OrderHeaderEntity;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.CreateOrderResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class OrderServiceMapper {


    public static final OrderServiceMapper INSTANCE = Mappers.getMapper(OrderServiceMapper.class);


    public abstract CreateOrderResponseDTO toCreateOrderResponseDTO(OrderHeaderEntity entity);

    public abstract GetOrderResponseDTO toGetOrderResponseDTO(OrderHeaderEntity entity);

    public abstract List<GetOrderResponseDTO> toGetOrderResponseDtoList(List<OrderHeaderEntity> entity);


}