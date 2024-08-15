package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequestDto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String urlImagen;
    private byte activo;
    private int idCategory;
    private int idRestaurant;
}
