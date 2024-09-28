package com.marien.backend.services.faq;

import com.marien.backend.dto.FAQDto;


public interface FAQService {

    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
