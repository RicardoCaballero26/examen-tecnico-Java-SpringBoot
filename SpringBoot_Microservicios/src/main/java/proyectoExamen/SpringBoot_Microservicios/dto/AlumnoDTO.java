package proyectoExamen.SpringBoot_Microservicios.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {

    private String matricula; // Generada automáticamente

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 50, message = "El apellido paterno no puede exceder 50 caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 50, message = "El apellido materno no puede exceder 50 caracteres")
    private String apellidoMaterno;

    @NotNull(message = "La materia es obligatoria")
    private Long materiaId;
}