package com.marien.backend.dto;

import lombok.Data;

import java.util.List;

@Data

public class ProductDetailDto {

    private ProductDto productDto;

    private List<ReviewDto> reviewDtoList;

    private List<FAQDto> faqDtoList;

}
