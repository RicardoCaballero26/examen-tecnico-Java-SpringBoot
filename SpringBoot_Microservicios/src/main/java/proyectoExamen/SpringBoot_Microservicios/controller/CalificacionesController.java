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
import proyectoExamen.SpringBoot_Microservicios.dto.CalificacionDTO;
import proyectoExamen.SpringBoot_Microservicios.service.CalificacionService;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@RequiredArgsConstructor
@Tag(name = "Calificaciones", description = "API para gestión de calificaciones")
public class CalificacionController {

    private final CalificacionService calificacionService;

    @Operation(summary = "Registrar nueva calificación",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Calificación creada"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            })
    @PostMapping
    public ResponseEntity<CalificacionDTO> registrarCalificacion(
            @Valid @RequestBody CalificacionDTO calificacionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(calificacionService.registrarCalificacion(calificacionDTO));
    }

    @Operation(summary = "Listar calificaciones por alumno",
            parameters = @Parameter(name = "matricula", description = "Matrícula del alumno"))
    @GetMapping("/alumno/{matricula}")
    public ResponseEntity<List<CalificacionDTO>> listarPorAlumno(
            @PathVariable String matricula) {
        return ResponseEntity.ok(calificacionService.listarPorAlumno(matricula));
    }

    @Operation(summary = "Actualizar calificación",
            parameters = @Parameter(name = "id", description = "ID de la calificación"))
    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> actualizarCalificacion(
            @PathVariable Long id,
            @Valid @RequestBody CalificacionDTO calificacionDTO) {
        return ResponseEntity.ok(calificacionService.actualizarCalificacion(id, calificacionDTO));
    }

    @Operation(summary = "Eliminar calificación",
            parameters = @Parameter(name = "id", description = "ID de la calificación"))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCalificacion(
            @PathVariable Long id) {
        calificacionService.eliminarCalificacion(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener calificaciones por materia",
            parameters = @Parameter(name = "materiaId", description = "ID de la materia"))
    @GetMapping("/materia/{materiaId}")
    public ResponseEntity<List<CalificacionDTO>> listarPorMateria(
            @PathVariable Long materiaId) {
        return ResponseEntity.ok(calificacionService.listarPorMateria(materiaId));
    }
}