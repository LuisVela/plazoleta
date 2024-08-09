package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "pedidos_platos")
public class PedidosPlato {
    @EmbeddedId
    private PedidosPlatoId id;

    @MapsId("idPedido")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false)
    private OrderEntity idPedido;

    @MapsId("idPlato")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id_plato", nullable = false)
    private DishEntity idPlato;

    @NotNull
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}