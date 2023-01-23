package com.ural.readingisgood.orderservice.service.model.orderservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersByUserIdResponseDTO {

    private List<GetOrderResponseDTO> orderList;
}
