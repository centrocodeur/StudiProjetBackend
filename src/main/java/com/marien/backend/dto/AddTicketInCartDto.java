package com.marien.backend.dto;


import lombok.Data;

@Data
public class AddTicketInCartDto {

    private Long userId;

    private Long ticketId;
}
