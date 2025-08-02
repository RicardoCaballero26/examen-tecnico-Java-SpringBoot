package proyecto1.examen.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}