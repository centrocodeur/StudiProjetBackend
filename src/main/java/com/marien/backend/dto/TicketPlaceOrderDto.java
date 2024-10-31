package com.marien.backend.dto;

import lombok.Data;

@Data
public class TicketPlaceOrderDto {
    private Long userId;

    private String email;

    private String orderDescription;
}
