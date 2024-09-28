package com.marien.backend.services.faq;


import com.marien.backend.dto.FAQDto;
import com.marien.backend.entity.FAQ;
import com.marien.backend.entity.Product;
import com.marien.backend.repository.FAQRepository;
import com.marien.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService{

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;


    public FAQDto postFAQ(Long productId, FAQDto faqDto){

        Optional<Product> optionalProduct= productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            FAQ faq = new FAQ();

            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFAQDto();
        }

        return null;
    }
}
