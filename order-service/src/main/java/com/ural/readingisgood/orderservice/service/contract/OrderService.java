package com.ural.readingisgood.orderservice.service.contract;

import com.ural.readingisgood.orderservice.service.model.orderservice.request.CreateOrderRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.GetOrdersByUserIdRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.CreateOrderResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderByQueryResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrdersByUserIdResponseDTO;

import java.util.Date;

public interface OrderService {


    GetOrderResponseDTO getById(Long id);

    GetOrderByQueryResponseDTO getByQuery(Date startDate, Date endDate);

    GetOrdersByUserIdResponseDTO getOrdersByUserId(GetOrdersByUserIdRequestDTO requestDTO);

    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO requestDTO);


}
