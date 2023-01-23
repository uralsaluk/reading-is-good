package com.ural.readingisgood.orderservice.controller.model.request;

import com.ural.readingisgood.orderservice.controller.validate.CheckQuantity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrderControllerRequest {

    @NotBlank(message = "mail field is required")
    private String mail;
    private String phone;

    @CheckQuantity
    @NotBlank
    private List<OrderedProductLineControllerDTO> orderedProductList;


}
