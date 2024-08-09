package com.pragma.powerup.application.dto.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RestaurantRequestDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String direccion;
    @NotBlank
    private Integer idPropietario;
    @NotBlank
    private String telefono;
    @NotBlank
    private String urlLogo;
    @NotBlank
    private Integer nit;
}
