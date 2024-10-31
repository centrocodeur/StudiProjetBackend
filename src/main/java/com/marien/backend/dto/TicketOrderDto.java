package com.marien.backend.dto;

import com.marien.backend.enums.OrderStatus;
import com.marien.backend.enums.TicketStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class TicketOrderDto {


    private Long id;

    private String ordersDescription;

    private Date date;

    private Double amount;

    private String email;

    private String payment;

    private TicketStatus ticketOrderStatus;

    private Double totalAmount;

    private Double discount;

    private UUID trackingId;

    private String userName;

    private List<TicketCartItemsDto> ticketCartItems;

    private String couponName;
}
