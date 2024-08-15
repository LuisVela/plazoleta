package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishStatusUpdateRequest;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class DishRestController {
    private final IDishHandler dishHandler;

    @Operation(summary = "Crea un nuevo plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plato creado", content = @Content),
    })
    @PostMapping("")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> saveDish(@RequestBody DishRequestDto dishRequestDto) {
        dishHandler.saveDish(dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Actualiza valores de la entidad plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plato actualizado", content = @Content),
    })
    @PreAuthorize("hasRole('Propietario')")
    @PatchMapping("")
    public ResponseEntity<Void> updateDish(@RequestBody DishUpdateRequestDto dishUpdateRequestDto) {
        dishHandler.updateDish(dishUpdateRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Habilitar/Deshabilitar Plato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plato actualizado", content = @Content),
    })
    @PreAuthorize("hasRole('Propietario')")
    @PatchMapping("")
    public ResponseEntity<Void> updateDishStatus(@RequestBody DishStatusUpdateRequest dishStatusUpdateRequest) {
        dishHandler.updateDishStatus(dishStatusUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
