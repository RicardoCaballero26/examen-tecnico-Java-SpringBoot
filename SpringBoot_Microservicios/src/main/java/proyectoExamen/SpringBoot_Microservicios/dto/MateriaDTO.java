package proyectoExamen.SpringBoot_Microservicios.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {

    private Long id;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Size(max = 10, message = "La clave no puede exceder 10 caracteres")
    @Pattern(regexp = "^[A-Z0-9-]*$", message = "La clave solo puede contener mayúsculas, números y guiones")
    private String clave;
}