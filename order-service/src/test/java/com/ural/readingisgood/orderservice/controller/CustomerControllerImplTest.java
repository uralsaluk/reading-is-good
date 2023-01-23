package com.ural.readingisgood.orderservice.controller;

import com.ural.readingisgood.orderservice.TestProps;
import com.ural.readingisgood.orderservice.controller.impl.CustomerControllerImpl;
import com.ural.readingisgood.orderservice.controller.model.request.GetOrdersByUserNameQueryModel;
import com.ural.readingisgood.orderservice.service.contract.CustomerService;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.GetOrdersByUserIdRequestDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrderResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.GetOrdersByUserIdResponseDTO;
import com.ural.readingisgood.orderservice.service.model.orderservice.response.OrderLineResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class CustomerControllerImplTest {


    @Mock
    private CustomerService customerService;


    @InjectMocks
    CustomerControllerImpl customerController;

    @BeforeEach
    public void init() {

        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void getOrdersByUserNameTest() {

        GetOrdersByUserNameQueryModel getOrdersByUserNameQueryModel = new GetOrdersByUserNameQueryModel();
        getOrdersByUserNameQueryModel.setUserName(TestProps.TEST_STRING);


        GetOrdersByUserIdRequestDTO getOrdersByUserIdRequestDTO = new GetOrdersByUserIdRequestDTO();
        getOrdersByUserIdRequestDTO.setUserName(TestProps.TEST_STRING);


        GetOrdersByUserIdResponseDTO getOrdersByUserIdResponseDTO = new GetOrdersByUserIdResponseDTO();
        GetOrderResponseDTO getOrderResponseDTO = new GetOrderResponseDTO();
        getOrderResponseDTO.setUserName(TestProps.TEST_STRING);
        OrderLineResponseDTO orderLineResponseDTO = new OrderLineResponseDTO();
        orderLineResponseDTO.setBookName(TestProps.TEST_STRING);
        getOrderResponseDTO.setItems(Arrays.asList(orderLineResponseDTO));
        getOrdersByUserIdResponseDTO.setOrderList(Arrays.asList(getOrderResponseDTO));


        when(customerService.getOrdersByUserId(getOrdersByUserIdRequestDTO)).thenReturn(getOrdersByUserIdResponseDTO);

        Assertions.assertEquals(HttpStatus.OK, customerController
                .getOrdersByUserName(getOrdersByUserNameQueryModel).getStatusCode());
        Assertions.assertEquals(TestProps.TEST_STRING
                , customerController.getOrdersByUserName(getOrdersByUserNameQueryModel)
                        .getBody().getOrderList().get(0).getUserName());
        Assertions.assertEquals(TestProps.TEST_STRING
                , customerController.getOrdersByUserName(getOrdersByUserNameQueryModel)
                        .getBody().getOrderList().get(0).getItems().get(0).getBookName());

    }

}
