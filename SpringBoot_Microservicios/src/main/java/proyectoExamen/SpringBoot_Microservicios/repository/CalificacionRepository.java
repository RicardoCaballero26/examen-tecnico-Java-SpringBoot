package proyectoExamen.SpringBoot_Microservicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoExamen.SpringBoot_Microservicios.dto.CalificacionDTO;
import proyectoExamen.SpringBoot_Microservicios.model.Calificacion;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    // Obtener calificaciones por alumno (DTO)
    @Query("SELECT new proyectoExamen.SpringBoot_Microservicios.dto.CalificacionDTO(" +
            "c.id, c.alumno.matricula, c.materia.id, c.valor, c.fechaRegistro) " +
            "FROM Calificacion c WHERE c.alumno.matricula = :matricula")
    List<CalificacionDTO> findByAlumnoMatricula(String matricula);

    // Obtener calificaciones por materia (DTO)
    @Query("SELECT new proyectoExamen.SpringBoot_Microservicios.dto.CalificacionDTO(" +
            "c.id, c.alumno.matricula, c.materia.id, c.valor, c.fechaRegistro) " +
            "FROM Calificacion c WHERE c.materia.id = :materiaId")
    List<CalificacionDTO> findByMateriaId(Long materiaId);

    // Obtener calificación específica por alumno y materia
    Optional<Calificacion> findByAlumnoMatriculaAndMateriaId(String matricula, Long materiaId);

    // Eliminar calificaciones por alumno
    @Modifying
    @Query("DELETE FROM Calificacion c WHERE c.alumno.matricula = :matricula")
    void deleteByAlumnoMatricula(String matricula);

    // Promedio de calificaciones por materia
    @Query("SELECT AVG(c.valor) FROM Calificacion c WHERE c.materia.id = :materiaId")
    Double calcularPromedioPorMateria(Long materiaId);

    // Verificar existencia de calificación
    boolean existsById(Long id);
}