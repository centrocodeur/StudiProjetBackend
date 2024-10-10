package com.marien.backend.services.admin.adminTicket;


import com.marien.backend.dto.ProductDto;
import com.marien.backend.dto.TicketDto;
import com.marien.backend.entity.Category;
import com.marien.backend.entity.Product;
import com.marien.backend.entity.Ticket;
import com.marien.backend.entity.TicketCategory;
import com.marien.backend.repository.TicketCategoryRepository;
import com.marien.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminTicketServiceImpl implements AdminTicketService{

    private final TicketRepository ticketRepository;

    private  final TicketCategoryRepository ticketCategoryRepository;


    public TicketDto addTicket(TicketDto ticketDto) throws IOException {

        Ticket ticket = new Ticket();

        ticket.setDescription(ticketDto.getDescription());

        ticket.setPrice(ticketDto.getPrice());
        ticket.setImg(ticketDto.getImg().getBytes());

        TicketCategory category = ticketCategoryRepository.findById(ticketDto.getCategoryId()).orElseThrow();

        ticket.setTicketCategory(category);

        return ticketRepository.save(ticket).getDto();



    }

    public List<TicketDto> getAllTicket(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(Ticket::getDto).collect(Collectors.toList());
    }


    public List<TicketDto>getAllProductByTitle(String title){
        List<Ticket> tickets = ticketRepository.findAllByTitleContaining(title);
        return tickets.stream().map(Ticket::getDto).collect(Collectors.toList());
    }

    public boolean deleteTicket(Long id){
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if(optionalTicket.isPresent()){
            ticketRepository.deleteById(id);
            return true;
        }
        return false;

    }

    public TicketDto getTicketById(Long ticketId){
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if(optionalTicket.isPresent()){
            return optionalTicket.get().getDto();
        }else {
            return null;
        }
    }



    public TicketDto updateTicket(Long ticketId, TicketDto ticketDto) throws IOException {
        Optional<Ticket> optionalTicket=ticketRepository.findById(ticketId);
        Optional<TicketCategory> optionalCategory = ticketCategoryRepository.findById(ticketDto.getCategoryId());

        if(optionalTicket.isPresent() && optionalCategory.isPresent()){
            Ticket ticket = optionalTicket.get();
            ticket.setTitle(ticketDto.getTitle());
            ticket.setPrice(ticketDto.getPrice());
            ticket.setDescription(ticketDto.getDescription());
            ticket.setTicketCategory(optionalCategory.get());

            if(ticketDto.getImg()!=null){
                ticket.setImg(ticketDto.getImg().getBytes());
            }
            return ticketRepository.save(ticket).getDto();

        }else {
            return null;
        }
    }

}
