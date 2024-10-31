package com.marien.backend.services.customer.ticket;

import com.marien.backend.dto.ProductDetailDto;
import com.marien.backend.dto.ProductDto;
import com.marien.backend.dto.TicketDto;
import com.marien.backend.entity.FAQ;
import com.marien.backend.entity.Product;
import com.marien.backend.entity.Review;
import com.marien.backend.entity.Ticket;
import com.marien.backend.repository.FAQRepository;
import com.marien.backend.repository.ProductRepository;
import com.marien.backend.repository.ReviewRepository;
import com.marien.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerTicketServiceImpl implements CustomerTicketService {

    private final TicketRepository ticketRepository;

    private  final FAQRepository faqRepository;

    private final ReviewRepository reviewRepository;

    public List<TicketDto> getAllTicket(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(Ticket::getDto).collect(Collectors.toList());
    }

    public List<TicketDto>searchTicketByTitle(String title){
        List<Ticket> tickets = ticketRepository.findAllByTitleContaining(title);
        return tickets.stream().map(Ticket::getDto).collect(Collectors.toList());
    }

}
