package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Size(max = 45)
    @NotNull
    @Column(name = "numero_documento", nullable = false, length = 45)
    private String numeroDocumento;

    @Size(max = 45)
    @NotNull
    @Column(name = "celular", nullable = false, length = 45)
    private String celular;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Size(max = 45)
    @NotNull
    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @Size(max = 45)
    @NotNull
    @Column(name = "clave", nullable = false, length = 45)
    private String clave;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", nullable = false)
    private RoleEntity idRol;

}