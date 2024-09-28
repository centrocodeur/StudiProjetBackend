package com.marien.backend.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {

    private Long id;

    private Long rating;

    @Lob
    private String description;

    private MultipartFile img;

    private byte[] returnedImg;



    private Long userId;


    private Long productId;

    private String username;



}
