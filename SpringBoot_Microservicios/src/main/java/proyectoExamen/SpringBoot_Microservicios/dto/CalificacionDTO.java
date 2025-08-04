package proyectoExamen.SpringBoot_Microservicios.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionDTO {

    private Long id;

    @NotBlank(message = "La matrícula del alumno es obligatoria")
    private String alumnoMatricula;

    @NotNull(message = "La materia es obligatoria")
    private Long materiaId;

    @DecimalMin(value = "0.0", message = "La calificación mínima es 0.0")
    @DecimalMax(value = "10.0", message = "La calificación máxima es 10.0")
    @NotNull(message = "La calificación es obligatoria")
    private Double valor;

    private LocalDateTime fechaRegistro;

    // Método conveniencia para obtener calificación
    public Double getCalificacion() {
        return this.valor;
    }

    // Método conveniencia para establecer calificación
    public void setCalificacion(Double valor) {
        this.valor = valor;
    }
}