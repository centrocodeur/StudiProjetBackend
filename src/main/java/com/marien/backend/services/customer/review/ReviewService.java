package com.marien.backend.services.customer.review;

import com.marien.backend.dto.OrderedProductsResponseDto;
import com.marien.backend.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
