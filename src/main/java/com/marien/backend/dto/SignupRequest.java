package com.marien.backend.dto;


import lombok.Data;

@Data
public class SignupRequest {

    private String email;

    private String password;

    //private String name;
    private String firstname;

    private String lastname;
}
