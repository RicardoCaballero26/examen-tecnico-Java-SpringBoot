package proyecto1.examen.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    // Matrícula generada automáticamente
    private String matricula;

    @PrePersist
    public void generarMatricula() {
        this.matricula = "ALU" + String.format("%04d", this.id);
    }
}