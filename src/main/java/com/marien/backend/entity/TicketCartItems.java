package com.marien.backend.entity;

import com.marien.backend.dto.CartItemsDto;
import com.marien.backend.dto.TicketCartItemsDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Data
public class TicketCartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private Double price;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    //@OneToOne(fetch = FetchType.LAZY)
    private TicketOrder order;


    public TicketCartItemsDto getCartDto(){
        TicketCartItemsDto ticketCartItemsDto =new TicketCartItemsDto();
        ticketCartItemsDto.setId(id);
        ticketCartItemsDto.setPrice(price);
        ticketCartItemsDto.setTicketId(ticket.getId());
        ticketCartItemsDto.setQuantity(quantity);
        ticketCartItemsDto.setUserId(user.getId());
        ticketCartItemsDto.setTicketTitle(ticket.getTitle());
        ticketCartItemsDto.setReturnedImg(ticket.getImg());

        return ticketCartItemsDto;
    }
}
