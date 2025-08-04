package proyectoExamen.SpringBoot_Microservicios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoExamen.SpringBoot_Microservicios.dto.CalificacionDTO;
import proyectoExamen.SpringBoot_Microservicios.exception.AlumnoNotFoundException;
import proyectoExamen.SpringBoot_Microservicios.exception.MateriaNotFoundException;
import proyectoExamen.SpringBoot_Microservicios.model.Calificacion;
import proyectoExamen.SpringBoot_Microservicios.repository.CalificacionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;
    private final AlumnoService alumnoService;
    private final MateriaService materiaService;

    @Transactional
    public CalificacionDTO registrarCalificacion(CalificacionDTO calificacionDTO) {
        // Validar existencia de alumno y materia
        if (!alumnoService.existeAlumno(calificacionDTO.getAlumnoMatricula())) {
            throw new AlumnoNotFoundException("Alumno no encontrado");
        }

        if (!materiaService.existeMateria(calificacionDTO.getMateriaId())) {
            throw new MateriaNotFoundException("Materia no encontrada");
        }

        // Mapear y guardar calificación
        Calificacion calificacion = mapToEntity(calificacionDTO);
        calificacion.setFechaRegistro(LocalDateTime.now());

        Calificacion saved = calificacionRepository.save(calificacion);
        return mapToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<CalificacionDTO> listarPorAlumno(String matricula) {
        return calificacionRepository.findByAlumnoMatricula(matricula).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CalificacionDTO> listarPorMateria(Long materiaId) {
        return calificacionRepository.findByMateriaId(materiaId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CalificacionDTO actualizarCalificacion(Long id, CalificacionDTO calificacionDTO) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));

        calificacion.setValor(calificacionDTO.getValor());
        calificacion.setFechaRegistro(LocalDateTime.now());

        Calificacion updated = calificacionRepository.save(calificacion);
        return mapToDTO(updated);
    }

    @Transactional
    public void eliminarCalificacion(Long id) {
        calificacionRepository.deleteById(id);
    }

    private Calificacion mapToEntity(CalificacionDTO dto) {
        return Calificacion.builder()
                .valor(dto.getValor())
                .alumno(alumnoService.getAlumnoEntity(dto.getAlumnoMatricula()))
                .materia(materiaService.getMateriaEntity(dto.getMateriaId()))
                .build();
    }

    private CalificacionDTO mapToDTO(Calificacion entity) {
        return CalificacionDTO.builder()
                .id(entity.getId())
                .valor(entity.getValor())
                .alumnoMatricula(entity.getAlumno().getMatricula())
                .materiaId(entity.getMateria().getId())
                .fechaRegistro(entity.getFechaRegistro())
                .build();
    }
}