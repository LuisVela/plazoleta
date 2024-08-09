package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Crear un nuevo propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Propietario Creado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Propietario ya existe", content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<ResponseDto<Boolean>> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        ResponseDto<Boolean> response = userHandler.saveUser(userRequestDto);

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
