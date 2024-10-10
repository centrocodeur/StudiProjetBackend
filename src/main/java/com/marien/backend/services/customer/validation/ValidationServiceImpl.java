package com.marien.backend.services.customer.validation;


import com.marien.backend.entity.User;
import com.marien.backend.entity.Validation;
import com.marien.backend.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service
public class ValidationServiceImpl implements ValidationService {

    private ValidationRepository validationRepository;


    private NotificationService notificationService;


    public void recordCustomer(User user){
        Validation validation = new Validation();
        validation.setUser(user);
        Instant creation =Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(20, MINUTES);
        validation.setExpiration(expiration);
        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d",randomInteger);

        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.sendMessage(validation);
    }

    public Validation readCode(String code){
        return this.validationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }



}
