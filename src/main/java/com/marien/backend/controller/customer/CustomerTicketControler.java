package com.marien.backend.controller.customer;


import com.marien.backend.dto.ProductDto;
import com.marien.backend.dto.TicketDto;
import com.marien.backend.services.customer.CustomerProductService;
import com.marien.backend.services.customer.ticket.CustomerTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerTicketControler {


    private final CustomerTicketService customerTicketService;

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        List<TicketDto> ticketDtos = customerTicketService.getAllTicket();

        return ResponseEntity.ok(ticketDtos);
    }

    @GetMapping("/ticket/search/{name}")
    public ResponseEntity<List<TicketDto>> getAllTicketByName(@PathVariable String name){
        List<TicketDto> ticketDtos = customerTicketService.searchTicketByTitle(name);

        return ResponseEntity.ok(ticketDtos);
    }
}
