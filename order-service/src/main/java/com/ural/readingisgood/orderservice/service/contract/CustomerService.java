package com.ural.readingisgood.orderservice.service.contract;

import com.ural.readingisgood.orderservice.service.model.orderservice.request.GetOrdersByUserIdRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrdersByUserIdResponseDTO;

public interface CustomerService {

    GetOrdersByUserIdResponseDTO getOrdersByUserId(GetOrdersByUserIdRequestDTO requestDTO);
}
