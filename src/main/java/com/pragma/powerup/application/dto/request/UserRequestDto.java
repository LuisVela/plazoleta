package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String numeroDocumento;
    @NotBlank
    @Size(max = 13, message = "El teléfono debe contener un máximo de 13 caracteres")
    @Pattern(regexp = "\\+?\\d{1,13}", message = "El teléfono puede contener el símbolo + y debe ser numérico")
    private String celular;
    @NotBlank
    private LocalDate fechaNacimiento;
    @NotBlank
    @Email(message = "El correo debe tener una estructura válida")
    private String correo;
    @NotBlank
    private String clave;
}
