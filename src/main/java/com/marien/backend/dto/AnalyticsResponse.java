package com.marien.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyticsResponse {
    private Long placed;

    private Long shipped;

    private Long delivered;

    private Long currentMonthOrders;  // currentMonthOrders

    private Long previousMonthOrders;

    private Long currentMonthEarnings;


    private Long previousMonthEarnings;
}
