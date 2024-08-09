package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "estado")
    private Boolean estado;

    @NotNull
    @Column(name = "id_chef", nullable = false)
    private Integer idChef;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity idRestaurante;

}