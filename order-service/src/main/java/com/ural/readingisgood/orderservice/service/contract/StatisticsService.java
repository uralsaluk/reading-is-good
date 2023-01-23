package com.ural.readingisgood.orderservice.service.contract;

import com.ural.readingisgood.orderservice.service.model.statisticsservice.request.GetMonthlyStatisticsRequestDTO;
import com.ural.readingisgood.orderservice.service.model.statisticsservice.response.GetMonthlyStatisticsResponseDTO;

public interface StatisticsService {


    GetMonthlyStatisticsResponseDTO getMonthlyStatistics(GetMonthlyStatisticsRequestDTO requestDTO);
}
