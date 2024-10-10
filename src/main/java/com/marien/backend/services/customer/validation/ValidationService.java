package com.marien.backend.services.customer.validation;


import com.marien.backend.entity.User;
import com.marien.backend.entity.Validation;

public interface ValidationService {

    void recordCustomer(User user);

    Validation readCode(String code);
}
