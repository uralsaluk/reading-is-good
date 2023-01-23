package com.ural.readingisgood.orderservice.controller.impl;


import com.ural.readingisgood.orderservice.controller.contract.OrderController;
import com.ural.readingisgood.orderservice.controller.mapper.OrderControllerMapper;
import com.ural.readingisgood.orderservice.controller.model.request.CreateOrderControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.request.QueryDateModel;
import com.ural.readingisgood.orderservice.controller.model.response.CreateOrderControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderByQueryControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderControllerResponse;
import com.ural.readingisgood.orderservice.service.contract.OrderService;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderByQueryResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class OrderControllerImpl implements OrderController {


    private static final OrderControllerMapper mapper = OrderControllerMapper.INSTANCE;

    private OrderService orderService;

    @Autowired
    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<CreateOrderControllerResponse> createOrder(@Valid CreateOrderControllerRequest request) {

        CreateOrderControllerResponse controllerResponse = mapper.toCreateOrderControllerResponse(
                orderService.createOrder(mapper.toCreateOrderRequestDTO(request)));


        return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetOrderControllerResponse> getById(Long orderId) {

        GetOrderResponseDTO getOrderResponseDTO = orderService.getById(orderId);
        GetOrderControllerResponse controllerResponse = mapper.toGetOrderControllerResponse(getOrderResponseDTO);
        ;
        return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetOrderByQueryControllerResponse> getByQuery(QueryDateModel dateRange) {
        GetOrderByQueryResponseDTO getOrderByQueryResponseDTO = orderService.getByQuery(dateRange.getStartDate(), dateRange.getEndDate());
        GetOrderByQueryControllerResponse controllerResponse = mapper.toGetOrderByQueryControllerResponse(getOrderByQueryResponseDTO);
        ;
        return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
    }
}
