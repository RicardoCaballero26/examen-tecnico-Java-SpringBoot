package proyecto1.examen.controller;

import proyecto1.examen.model.Alumno;
import proyecto1.examen.service.AlumnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public Alumno registrarAlumno(@RequestBody Alumno alumno) {
        return alumnoService.registrarAlumno(alumno);
    }

    @GetMapping
    public List<Alumno> obtenerTodosAlumnos() {
        return alumnoService.obtenerTodosAlumnos();
    }
}