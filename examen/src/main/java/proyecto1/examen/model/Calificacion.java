package proyecto1.examen.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    private Double valor;

    // Constructor sin parámetros
    public Calificacion() {}

    // Constructor con parámetros
    public Calificacion(Alumno alumno, Materia materia, Double valor) {
        this.alumno = alumno;
        this.materia = materia;
        this.valor = valor;
    }
}