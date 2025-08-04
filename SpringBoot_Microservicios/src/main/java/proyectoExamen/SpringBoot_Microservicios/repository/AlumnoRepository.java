package proyectoExamen.SpringBoot_Microservicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoExamen.SpringBoot_Microservicios.dto.AlumnoDTO;
import proyectoExamen.SpringBoot_Microservicios.model.Alumno;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {

    // Buscar por matrícula (retorna DTO)
    @Query("SELECT new proyectoExamen.SpringBoot_Microservicios.dto.AlumnoDTO(" +
            "a.matricula, a.nombre, a.apellidoPaterno, a.apellidoMaterno, a.materia.id) " +
            "FROM Alumno a WHERE a.matricula = :matricula")
    Optional<AlumnoDTO> findDtoByMatricula(String matricula);

    // Listar todos los alumnos (DTO)
    @Query("SELECT new proyectoExamen.SpringBoot_Microservicios.dto.AlumnoDTO(" +
            "a.matricula, a.nombre, a.apellidoPaterno, a.apellidoMaterno, a.materia.id) " +
            "FROM Alumno a")
    List<AlumnoDTO> findAllAlumnosDto();

    // Buscar alumnos por materia
    List<Alumno> findByMateriaId(Long materiaId);

    // Verificar existencia por nombres
    boolean existsByNombreAndApellidoPaternoAndApellidoMaterno(
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno);

    // Verificar existencia por matrícula
    boolean existsByMatricula(String matricula);

    // Buscar entidad por matrícula
    Optional<Alumno> findByMatricula(String matricula);
}
