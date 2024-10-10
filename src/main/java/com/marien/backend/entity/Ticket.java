package com.marien.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marien.backend.dto.ProductDto;
import com.marien.backend.dto.TicketDto;
import com.marien.backend.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    private Double price;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TicketCategory ticketCategory;

    public TicketDto getDto(){
        TicketDto ticketDto= new TicketDto();

        ticketDto.setId(id);

        ticketDto.setPrice(price);
        ticketDto.setDescription(description);
        ticketDto.setByteImg(img);
        ticketDto.setCategoryId(ticketCategory.getId());
        ticketDto.setCategoryName(ticketCategory.getName());

        return ticketDto;
    }

}
