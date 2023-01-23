package com.ural.readingisgood.orderservice.controller.contract;

import com.ural.readingisgood.orderservice.controller.model.request.GetOrdersByUserNameQueryModel;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderByUserNameControllerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("api/v0/customer")
public interface CustomerController {

    @GetMapping("/getOrders")
    ResponseEntity<GetOrderByUserNameControllerResponse> getOrdersByUserName(@Valid GetOrdersByUserNameQueryModel dateRange);
}
