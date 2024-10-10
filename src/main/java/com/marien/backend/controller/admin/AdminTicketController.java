package com.marien.backend.controller.admin;


import com.marien.backend.dto.FAQDto;
import com.marien.backend.dto.ProductDto;
import com.marien.backend.dto.TicketDto;
import com.marien.backend.services.admin.adminTicket.AdminTicketService;
import com.marien.backend.services.admin.adminproduct.AdminProductService;
import com.marien.backend.services.faq.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminTicketController {


    private final AdminTicketService adminTicketService;


    private final FAQService faqService;


    @PostMapping("/ticket")
    public ResponseEntity<TicketDto> addTicket(@ModelAttribute TicketDto ticketDto) throws IOException {

        TicketDto ticketDto1= adminTicketService.addTicket(ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDto1);
    }


    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        List<TicketDto> ticketDtos = adminTicketService.getAllTicket();

        return ResponseEntity.ok(ticketDtos);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<TicketDto>> getAllTicketByTitle(@PathVariable String title){
        List<TicketDto> ticketDtos = adminTicketService.getAllProductByTitle(title);

        return ResponseEntity.ok(ticketDtos);
    }

    @DeleteMapping("/ticket/{ticketId}")
    public  ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId){
        boolean deleted = adminTicketService.deleteTicket(ticketId);

        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/faq/{ticketId}")
    public ResponseEntity<FAQDto> postFAQ(@PathVariable Long ticketId, @RequestBody FAQDto faqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(ticketId, faqDto));
    }


    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long ticketId){
        TicketDto ticketDto= adminTicketService.getTicketById(ticketId);
        if(ticketDto!=null){
            return ResponseEntity.ok(ticketDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long ticketId, @ModelAttribute TicketDto ticketDto) throws IOException {
        TicketDto updatedTicket = adminTicketService.updateTicket(ticketId, ticketDto);

        if(updatedTicket!=null){
            return ResponseEntity.ok(updatedTicket);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
