package com.pragma.powerup.infrastructure.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private String message;
    private String jwt;
    private Boolean status;
}
