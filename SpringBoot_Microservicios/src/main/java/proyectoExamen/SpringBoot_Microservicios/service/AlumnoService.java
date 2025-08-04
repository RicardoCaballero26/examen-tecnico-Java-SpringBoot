package proyectoExamen.SpringBoot_Microservicios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoExamen.SpringBoot_Microservicios.dto.AlumnoDTO;
import proyectoExamen.SpringBoot_Microservicios.exception.DuplicateAlumnoException;
import proyectoExamen.SpringBoot_Microservicios.exception.MateriaNotFoundException;
import proyectoExamen.SpringBoot_Microservicios.model.Alumno;
import proyectoExamen.SpringBoot_Microservicios.model.Materia;
import proyectoExamen.SpringBoot_Microservicios.repository.AlumnoRepository;
import proyectoExamen.SpringBoot_Microservicios.repository.MateriaRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final MateriaRepository materiaRepository;

    @Transactional
    public AlumnoDTO registrarAlumno(AlumnoDTO alumnoDTO) {
        // Validar si el alumno ya existe
        if (alumnoRepository.existsByNombreAndApellidoPaternoAndApellidoMaterno(
                alumnoDTO.getNombre(),
                alumnoDTO.getApellidoPaterno(),
                alumnoDTO.getApellidoMaterno())) {
            throw new DuplicateAlumnoException("El alumno ya está registrado");
        }

        // Obtener materia
        Materia materia = materiaRepository.findById(alumnoDTO.getMateriaId())
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));

        // Generar matrícula
        String matricula = "ALU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Crear y guardar alumno
        Alumno alumno = Alumno.builder()
                .matricula(matricula)
                .nombre(alumnoDTO.getNombre())
                .apellidoPaterno(alumnoDTO.getApellidoPaterno())
                .apellidoMaterno(alumnoDTO.getApellidoMaterno())
                .materia(materia)
                .build();

        Alumno savedAlumno = alumnoRepository.save(alumno);

        return toDto(savedAlumno);
    }

    @Transactional(readOnly = true)
    public List<AlumnoDTO> listarAlumnos() {
        return alumnoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlumnoDTO buscarPorMatricula(String matricula) {
        Alumno alumno = alumnoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        return toDto(alumno);
    }

    @Transactional(readOnly = true)
    public boolean existeAlumno(String matricula) {
        return alumnoRepository.existsByMatricula(matricula);
    }

    @Transactional(readOnly = true)
    public Alumno getAlumnoEntity(String matricula) {
        return alumnoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    private AlumnoDTO toDto(Alumno alumno) {
        return AlumnoDTO.builder()
                .matricula(alumno.getMatricula())
                .nombre(alumno.getNombre())
                .apellidoPaterno(alumno.getApellidoPaterno())
                .apellidoMaterno(alumno.getApellidoMaterno())
                .materiaId(alumno.getMateria().getId())
                .build();
    }
}