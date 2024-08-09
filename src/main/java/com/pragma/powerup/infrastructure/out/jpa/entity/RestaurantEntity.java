package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "restaurantes")
public class RestaurantEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Size(max = 45)
    @Column(name = "direccion", length = 45)
    private String direccion;

    @NotNull
    @Column(name = "id_propietario", nullable = false)
    private Integer idPropietario;

    @Size(max = 45)
    @Column(name = "telefono", length = 45)
    private String telefono;

    @Size(max = 45)
    @Column(name = "urlLogo", length = 45)
    private String urlLogo;

    @Column(name = "nit")
    private Integer nit;
}