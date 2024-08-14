package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.domain.usecase.CustomUserDetailsService;
import com.pragma.powerup.infrastructure.security.dto.AuthLoginRequest;
import com.pragma.powerup.infrastructure.security.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("denyAll()")
public class AuthController {
    @Autowired()
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }
}
