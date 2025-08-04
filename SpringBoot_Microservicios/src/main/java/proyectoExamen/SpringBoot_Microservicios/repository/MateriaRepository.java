package proyectoExamen.SpringBoot_Microservicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoExamen.SpringBoot_Microservicios.dto.MateriaDTO;
import proyectoExamen.SpringBoot_Microservicios.model.Materia;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    // Buscar por nombre
    Optional<Materia> findByNombre(String nombre);

    // Buscar por clave
    Optional<Materia> findByClave(String clave);

    // Listar todas las materias (DTO)
    @Query("SELECT new proyectoExamen.SpringBoot_Microservicios.dto.MateriaDTO(" +
            "m.id, m.nombre, m.clave) FROM Materia m")
    List<MateriaDTO> findAllMateriasDto();

    // Verificar existencia por ID
    boolean existsById(Long id);

    // Verificar existencia por nombre
    boolean existsByNombre(String nombre);

    // Verificar existencia por clave
    boolean existsByClave(String clave);
}