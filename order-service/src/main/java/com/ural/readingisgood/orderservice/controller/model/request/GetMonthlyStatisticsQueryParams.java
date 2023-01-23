package com.ural.readingisgood.orderservice.controller.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
public class GetMonthlyStatisticsQueryParams {

    @NotEmpty(message = "userName field is required")
    private String userName;

    private Integer year;
}
