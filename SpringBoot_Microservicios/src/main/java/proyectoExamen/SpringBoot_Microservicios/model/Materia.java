package proyectoExamen.SpringBoot_Microservicios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 10, unique = true)
    private String clave;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alumno> alumnos = new ArrayList<>();

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Calificacion> calificaciones = new ArrayList<>();

    // Método helper para agregar alumno
    public void addAlumno(Alumno alumno) {
        alumnos.add(alumno);
        alumno.setMateria(this);
    }

    // Método helper para agregar calificación
    public void addCalificacion(Calificacion calificacion) {
        calificaciones.add(calificacion);
        calificacion.setMateria(this);
    }
}