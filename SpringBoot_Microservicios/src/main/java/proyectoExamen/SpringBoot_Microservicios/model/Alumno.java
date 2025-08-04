package proyectoExamen.SpringBoot_Microservicios.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "matricula", length = 36, nullable = false, updatable = false)
    private String matricula;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Calificacion> calificaciones;
}