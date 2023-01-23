package com.ural.readingisgood.orderservice.controller.mapper;

import com.ural.readingisgood.orderservice.controller.model.request.GetMonthlyStatisticsQueryParams;
import com.ural.readingisgood.orderservice.controller.model.response.GetMonthlyStatisticsControllerResponse;
import com.ural.readingisgood.orderservice.service.model.statisticsservice.request.GetMonthlyStatisticsRequestDTO;
import com.ural.readingisgood.orderservice.service.model.statisticsservice.response.GetMonthlyStatisticsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class StatisticsControllerMapper {

    public static final StatisticsControllerMapper INSTANCE = Mappers.getMapper(StatisticsControllerMapper.class);


    public abstract GetMonthlyStatisticsRequestDTO toGetMonthlyStatisticsRequestDTO(GetMonthlyStatisticsQueryParams request);

    public abstract GetMonthlyStatisticsControllerResponse toGetMonthlyStatisticsControllerResponse(GetMonthlyStatisticsResponseDTO responseDTO);


}
