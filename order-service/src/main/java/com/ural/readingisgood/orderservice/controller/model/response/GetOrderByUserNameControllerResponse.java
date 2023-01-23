package com.ural.readingisgood.orderservice.controller.model.response;

import com.ural.readingisgood.orderservice.controller.model.OrderControllerDTO;
import com.ural.readingisgood.orderservice.core.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderByUserNameControllerResponse extends BaseResponse {

    private List<OrderControllerDTO> orderList;
}
