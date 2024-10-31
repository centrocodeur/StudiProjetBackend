package com.marien.backend.services.customer.ticket;

import com.marien.backend.dto.TicketDto;

import java.util.List;

public interface CustomerTicketService {

    List<TicketDto> getAllTicket();

    List<TicketDto>searchTicketByTitle(String title);

}
