package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "platos")
public class DishEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Size(max = 45)
    @Column(name = "descripcion", length = 45)
    private String descripcion;

    @NotNull
    @Column(name = "precio", nullable = false)
    private Double precio;

    @Size(max = 45)
    @Column(name = "url_imagen", length = 45)
    private String urlImagen;

    @NotNull
    @Column(name = "activo", nullable = false)
    private Byte activo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoryEntity idCategoria;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity idRestaurante;
}