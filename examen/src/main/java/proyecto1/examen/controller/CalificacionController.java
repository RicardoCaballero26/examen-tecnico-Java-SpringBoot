package proyecto1.examen.controller;

import proyecto1.examen.model.Calificacion;
import proyecto1.examen.service.CalificacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {
    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @PostMapping
    public Calificacion registrarCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionService.registrarCalificacion(calificacion);
    }

    @PutMapping("/{id}")
    public Calificacion actualizarCalificacion(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        return calificacionService.actualizarCalificacion(id, calificacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarCalificacion(@PathVariable Long id) {
        calificacionService.eliminarCalificacion(id);
    }

    @GetMapping
    public List<Calificacion> obtenerTodasCalificaciones() {
        return calificacionService.obtenerTodasCalificaciones();
    }
}