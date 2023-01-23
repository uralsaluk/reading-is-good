package com.ural.readingisgood.orderservice.service.mapper;

import com.ural.readingisgood.orderservice.entity.MonthlyStatisticsDAOModel;
import com.ural.readingisgood.orderservice.service.model.statisticsservice.MonthlyStatisticsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class StatisticsServiceMapper {


    public static final StatisticsServiceMapper INSTANCE = Mappers.getMapper(StatisticsServiceMapper.class);


    public abstract MonthlyStatisticsDTO toMonthlyStatisticsDTO(MonthlyStatisticsDAOModel entity);

    public abstract List<MonthlyStatisticsDTO> toMonthlyStatisticsDTOList(List<MonthlyStatisticsDAOModel> entity);


}