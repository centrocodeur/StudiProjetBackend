package com.marien.backend.dto;


import com.marien.backend.enums.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    //private String name;

    private UserRole userRole;

    private UUID userTrackingId;

    private boolean activated= false;
}
