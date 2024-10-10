package com.marien.backend.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticket_category")
@Data
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;
}
