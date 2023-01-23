package com.ural.readingisgood.orderservice.controller.validate;

import com.ural.readingisgood.orderservice.controller.model.request.OrderedProductLineControllerDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class QuantityValidator implements ConstraintValidator<CheckQuantity, List<OrderedProductLineControllerDTO>> {


    @Override
    public boolean isValid(List<OrderedProductLineControllerDTO> orderedProductList, ConstraintValidatorContext context) {


        return !orderedProductList.stream().anyMatch(x -> x.getQuantity() < 0);

    }
}