package com.ural.readingisgood.orderservice.controller.model.response;

import com.ural.readingisgood.orderservice.core.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateInventoryControllerResponse extends BaseResponse {

    private Long id;

    private Integer stock;
}
