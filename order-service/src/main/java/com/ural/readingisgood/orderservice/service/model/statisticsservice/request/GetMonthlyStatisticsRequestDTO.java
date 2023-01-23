package com.ural.readingisgood.orderservice.service.model.statisticsservice.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetMonthlyStatisticsRequestDTO {

    private String userName;
    private Integer year;
}
