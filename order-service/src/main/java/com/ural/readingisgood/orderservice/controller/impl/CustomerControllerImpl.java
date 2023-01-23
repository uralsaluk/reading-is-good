package com.ural.readingisgood.orderservice.controller.impl;

import com.ural.readingisgood.orderservice.controller.contract.CustomerController;
import com.ural.readingisgood.orderservice.controller.mapper.CustomerControllerMapper;
import com.ural.readingisgood.orderservice.controller.model.request.GetOrdersByUserNameQueryModel;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderByUserNameControllerResponse;
import com.ural.readingisgood.orderservice.service.contract.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerControllerImpl implements CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    private static final CustomerControllerMapper mapper = CustomerControllerMapper.INSTANCE;

    @Override
    public ResponseEntity<GetOrderByUserNameControllerResponse> getOrdersByUserName(GetOrdersByUserNameQueryModel queryModel) {

        GetOrderByUserNameControllerResponse controllerResponse = mapper
                .toGetOrderByUserNameControllerResponse(customerService
                        .getOrdersByUserId(mapper.toGetOrdersByUserIdRequestDTO(queryModel)));


        return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
    }
}
