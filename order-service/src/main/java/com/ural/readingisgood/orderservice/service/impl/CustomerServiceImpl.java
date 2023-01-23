package com.ural.readingisgood.orderservice.service.impl;

import com.ural.readingisgood.orderservice.service.contract.CustomerService;
import com.ural.readingisgood.orderservice.service.contract.OrderService;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.GetOrdersByUserIdRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrdersByUserIdResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private OrderService orderService;

    @Autowired
    public CustomerServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public GetOrdersByUserIdResponseDTO getOrdersByUserId(GetOrdersByUserIdRequestDTO requestDTO) {


        return orderService.getOrdersByUserId(requestDTO);


    }
}
