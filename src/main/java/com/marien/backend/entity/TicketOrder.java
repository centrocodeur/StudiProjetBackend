package com.marien.backend.entity;

import com.marien.backend.dto.OrderDto;
import com.marien.backend.dto.TicketOrderDto;
import com.marien.backend.enums.OrderStatus;
import com.marien.backend.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "ticket_orders")
public class TicketOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /*
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

     */



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")  // ok live
    private List<TicketCartItems> cartItems;

    public TicketOrderDto getOrderDto(){
        TicketOrderDto ticketOrderDto = new TicketOrderDto();
        ticketOrderDto.setId(id);
        ticketOrderDto.setOrdersDescription(ordersDescription);
        ticketOrderDto.setEmail(email);
        ticketOrderDto.setTrackingId(trackingId);
        ticketOrderDto.setAmount(amount);
        ticketOrderDto.setDate(date);
        ticketOrderDto.setTicketOrderStatus(ticketOrderStatus);
        ticketOrderDto.setUserName(user.getLastname());
        if (coupon!=null){
            ticketOrderDto.setCouponName(coupon.getName());
        }

        return ticketOrderDto;
    }
}
