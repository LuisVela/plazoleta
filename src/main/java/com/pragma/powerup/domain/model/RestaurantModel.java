package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantModel {
    private int id;
    private String nombre;
    private String direccion;
    private Integer idPropietario;
    private String telefono;
    private String urlLogo;
    private Integer nit;
}
