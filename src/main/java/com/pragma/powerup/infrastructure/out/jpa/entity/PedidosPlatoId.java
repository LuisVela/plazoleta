package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PedidosPlatoId implements java.io.Serializable {
    private static final long serialVersionUID = 3314175195868575424L;
    @NotNull
    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

    @NotNull
    @Column(name = "Id_plato", nullable = false)
    private Integer idPlato;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PedidosPlatoId entity = (PedidosPlatoId) o;
        return Objects.equals(this.idPlato, entity.idPlato) &&
                Objects.equals(this.idPedido, entity.idPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlato, idPedido);
    }

}