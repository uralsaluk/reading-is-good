package com.ural.readingisgood.orderservice.service.model.statisticsservice.response;

import com.ural.readingisgood.orderservice.service.model.statisticsservice.MonthlyStatisticsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMonthlyStatisticsResponseDTO {

    private List<MonthlyStatisticsDTO> statisticList;
}
