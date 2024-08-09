package com.pragma.powerup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishModel {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String urlImagen;
    private Byte activo;
    private CategoryModel category;
    private RestaurantModel restaurant;
}
