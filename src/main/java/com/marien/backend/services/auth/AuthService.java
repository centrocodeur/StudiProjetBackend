package com.marien.backend.services.auth;

import com.marien.backend.dto.SignupRequest;
import com.marien.backend.dto.UserDto;

import java.util.Map;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);

   void accountActivation(Map<String, String> activation);
}
