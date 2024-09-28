package com.marien.backend.services.auth;

import com.marien.backend.dto.SignupRequest;
import com.marien.backend.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
