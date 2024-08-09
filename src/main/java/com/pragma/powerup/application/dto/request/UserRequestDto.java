package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.Utilities.Message;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = Message.MANDATORY_FIELD_NAME)
    private String nombre;
    @NotBlank(message = Message.MANDATORY_FIELD_LAST_NAME)
    private String apellido;
    @NotBlank(message = Message.MANDATORY_FIELD_DOCUMENT)
    private String numeroDocumento;
    @NotBlank(message = Message.MANDATORY_FIELD_CELL)
    @Size(max = 13, message = "El teléfono debe contener un máximo de 13 caracteres")
    @Pattern(regexp = "\\+?\\d{1,13}", message = "El teléfono puede contener el símbolo + y debe ser numérico")
    private String celular;
    @NotNull(message = Message.MANDATORY_FIELD_BIRTHDAY)
    private LocalDate fechaNacimiento;
    @NotBlank(message = Message.MANDATORY_FIELD_EMAIL)
    @Email(message = "El correo debe tener una estructura válida")
    private String correo;
    @NotBlank(message = Message.MANDATORY_FIELD_PASSWORD)
    private String clave;
}
