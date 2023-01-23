package com.ural.readingisgood.orderservice.controller.contract;

import com.ural.readingisgood.orderservice.controller.model.request.CreateOrderControllerRequest;
import com.ural.readingisgood.orderservice.controller.model.request.QueryDateModel;
import com.ural.readingisgood.orderservice.controller.model.response.CreateOrderControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderByQueryControllerResponse;
import com.ural.readingisgood.orderservice.controller.model.response.GetOrderControllerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("api/v0/order")
public interface OrderController {


    @PostMapping()
    ResponseEntity<CreateOrderControllerResponse> createOrder(@RequestBody @Valid CreateOrderControllerRequest request);

    @GetMapping("{orderId}")
    ResponseEntity<GetOrderControllerResponse> getById(@PathVariable Long orderId);


    @GetMapping
    ResponseEntity<GetOrderByQueryControllerResponse> getByQuery(@Valid QueryDateModel dateRange);

}
