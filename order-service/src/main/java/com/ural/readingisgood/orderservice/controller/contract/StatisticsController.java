package com.ural.readingisgood.orderservice.controller.contract;

import com.ural.readingisgood.orderservice.controller.model.request.GetMonthlyStatisticsQueryParams;
import com.ural.readingisgood.orderservice.controller.model.response.GetMonthlyStatisticsControllerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("api/v0/statistics")
public interface StatisticsController {


    @GetMapping
    ResponseEntity<GetMonthlyStatisticsControllerResponse>
    getMonthlyStatistics(@Valid GetMonthlyStatisticsQueryParams queryParams);


}
