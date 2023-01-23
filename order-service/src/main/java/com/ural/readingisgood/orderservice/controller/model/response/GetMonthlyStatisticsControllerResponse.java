package com.ural.readingisgood.orderservice.controller.model.response;

import com.ural.readingisgood.orderservice.controller.model.MonthlyStatisticsControllerDTO;
import com.ural.readingisgood.orderservice.core.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMonthlyStatisticsControllerResponse extends BaseResponse {

    private List<MonthlyStatisticsControllerDTO> statisticList;
}
