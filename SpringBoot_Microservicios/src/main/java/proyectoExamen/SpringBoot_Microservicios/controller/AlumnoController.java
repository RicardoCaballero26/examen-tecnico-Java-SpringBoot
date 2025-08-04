package proyectoExamen.SpringBoot_Microservicios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoExamen.SpringBoot_Microservicios.dto.AlumnoDTO;
import proyectoExamen.SpringBoot_Microservicios.service.AlumnoService;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
@Tag(name = "Alumnos", description = "API para gestión de alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Registrar nuevo alumno",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Alumno creado"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            })
    @PostMapping
    public ResponseEntity<AlumnoDTO> registrarAlumno(
            @Valid @RequestBody AlumnoDTO alumnoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alumnoService.registrarAlumno(alumnoDTO));
    }

    @Operation(summary = "Obtener lista de alumnos")
    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> listarAlumnos() {
        return ResponseEntity.ok(alumnoService.listarAlumnos());
    }

    @Operation(summary = "Buscar alumno por matrícula",
            parameters = @Parameter(name = "matricula", description = "Matrícula del alumno"))
    @GetMapping("/{matricula}")
    public ResponseEntity<AlumnoDTO> buscarPorMatricula(
            @PathVariable String matricula) {
        return ResponseEntity.ok(alumnoService.buscarPorMatricula(matricula));
    }

    @Operation(summary = "Actualizar alumno")
    @PutMapping("/{matricula}")
    public ResponseEntity<AlumnoDTO> actualizarAlumno(
            @PathVariable String matricula,
            @Valid @RequestBody AlumnoDTO alumnoDTO) {
        return ResponseEntity.ok(alumnoService.actualizarAlumno(matricula, alumnoDTO));
    }

    @Operation(summary = "Eliminar alumno")
    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> eliminarAlumno(
            @PathVariable String matricula) {
        alumnoService.eliminarAlumno(matricula);
        return ResponseEntity.noContent().build();
    }
}