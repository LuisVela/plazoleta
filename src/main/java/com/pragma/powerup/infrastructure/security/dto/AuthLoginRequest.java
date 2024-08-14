package com.pragma.powerup.infrastructure.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class AuthLoginRequest {
    @NotBlank private String username;
    @NotBlank private String password;
}