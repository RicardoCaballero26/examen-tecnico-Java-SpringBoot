package proyecto1.examen.service;

import proyecto1.examen.model.Alumno;
import proyecto1.examen.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public Alumno registrarAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public List<Alumno> obtenerTodosAlumnos() {
        return alumnoRepository.findAll();
    }
}