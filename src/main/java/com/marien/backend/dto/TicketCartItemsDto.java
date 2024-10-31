package com.marien.backend.dto;

import lombok.Data;

@Data
public class TicketCartItemsDto {


    private Long id;

    private Double price;

    private Long quantity;

    private Long ticketId;

    private Long orderId;

    private String ticketTitle;

    private byte [] returnedImg;

    private Long userId;
}
