package com.ural.readingisgood.orderservice.controller.impl;

import com.ural.readingisgood.orderservice.controller.contract.StatisticsController;
import com.ural.readingisgood.orderservice.controller.mapper.StatisticsControllerMapper;
import com.ural.readingisgood.orderservice.controller.model.request.GetMonthlyStatisticsQueryParams;
import com.ural.readingisgood.orderservice.controller.model.response.GetMonthlyStatisticsControllerResponse;
import com.ural.readingisgood.orderservice.service.contract.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsControllerImpl implements StatisticsController {

    private StatisticsService statisticsService;

    private final static StatisticsControllerMapper mapper = StatisticsControllerMapper.INSTANCE;

    @Autowired
    public StatisticsControllerImpl(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    public ResponseEntity<GetMonthlyStatisticsControllerResponse> getMonthlyStatistics(GetMonthlyStatisticsQueryParams queryParams) {

        GetMonthlyStatisticsControllerResponse controllerResponse = mapper.toGetMonthlyStatisticsControllerResponse(
                statisticsService.getMonthlyStatistics(mapper.toGetMonthlyStatisticsRequestDTO(queryParams)));


        return new ResponseEntity<>(controllerResponse, HttpStatus.OK);
    }
}
