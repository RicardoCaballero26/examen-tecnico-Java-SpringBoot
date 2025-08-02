package proyecto1.examen.repository;

import proyecto1.examen.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByAlumnoId(Long alumnoId);
    List<Calificacion> findByMateriaId(Long materiaId);
    List<Calificacion> findByAlumnoIdAndMateriaId(Long alumnoId, Long materiaId);
}