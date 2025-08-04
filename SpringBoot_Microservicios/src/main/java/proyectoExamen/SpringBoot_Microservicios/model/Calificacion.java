package proyectoExamen.SpringBoot_Microservicios.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_matricula", nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @Column(nullable = false, precision = 3, scale = 1)
    private Double valor;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    // Métodos específicos para manejar la calificación
    public Double getCalificacion() {
        return this.valor;
    }

    public void setCalificacion(Double valor) {
        this.valor = valor;
    }
}