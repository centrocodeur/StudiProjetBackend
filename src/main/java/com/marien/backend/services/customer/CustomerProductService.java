package com.marien.backend.services.customer;

import com.marien.backend.dto.ProductDetailDto;
import com.marien.backend.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> getAllProduct();

    List<ProductDto>searchProductByTitle(String name);

    ProductDetailDto getProductDetailById(Long productId);

}
