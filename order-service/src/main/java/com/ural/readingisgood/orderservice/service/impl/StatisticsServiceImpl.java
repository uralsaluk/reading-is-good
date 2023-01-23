package com.ural.readingisgood.orderservice.service.impl;

import com.ural.readingisgood.orderservice.entity.MonthlyStatisticsDAOModel;
import com.ural.readingisgood.orderservice.repository.StatisticsCustomRepository;
import com.ural.readingisgood.orderservice.repository.StatisticsRepository;
import com.ural.readingisgood.orderservice.service.contract.StatisticsService;
import com.ural.readingisgood.orderservice.service.mapper.StatisticsServiceMapper;
import com.ural.readingisgood.orderservice.service.model.statisticsservice.MonthlyStatisticsDTO;
import com.ural.readingisgood.orderservice.service.model.statisticsservice.request.GetMonthlyStatisticsRequestDTO;
import com.ural.readingisgood.orderservice.service.model.statisticsservice.response.GetMonthlyStatisticsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final static StatisticsServiceMapper mapper = StatisticsServiceMapper.INSTANCE;

    @Autowired
    private StatisticsRepository statisticsRepository;


    @Autowired
    private StatisticsCustomRepository customRepository;


    @Override
    public GetMonthlyStatisticsResponseDTO getMonthlyStatistics(GetMonthlyStatisticsRequestDTO requestDTO) {

        List<MonthlyStatisticsDAOModel> statisticDAOList = customRepository.getMonthlyStatisticsByUserId(requestDTO.getUserName(), requestDTO.getYear());

        List<MonthlyStatisticsDTO> statisticsDTOList = mapper.toMonthlyStatisticsDTOList(statisticDAOList);


        return new GetMonthlyStatisticsResponseDTO(statisticsDTOList);

    }
}
