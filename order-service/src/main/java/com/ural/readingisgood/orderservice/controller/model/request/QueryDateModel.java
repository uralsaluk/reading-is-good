package com.ural.readingisgood.orderservice.controller.model.request;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class QueryDateModel {

    @NotNull(message = "StartDate is required field")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date startDate;

    @NotNull(message = "StartDate is required field")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date endDate;
}
