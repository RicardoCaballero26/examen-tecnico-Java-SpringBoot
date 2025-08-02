package proyecto1.examen.service;

import proyecto1.examen.model.Calificacion;
import proyecto1.examen.repository.CalificacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CalificacionService {
    private final CalificacionRepository calificacionRepository;

    public CalificacionService(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    public Calificacion registrarCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    public Calificacion actualizarCalificacion(Long id, Calificacion calificacionDetails) {
        return calificacionRepository.findById(id)
                .map(calificacion -> {
                    if (calificacionDetails.getAlumno() != null) {
                        calificacion.setAlumno(calificacionDetails.getAlumno());
                    }
                    if (calificacionDetails.getMateria() != null) {
                        calificacion.setMateria(calificacionDetails.getMateria());
                    }
                    if (calificacionDetails.getValor() != null) {
                        calificacion.setValor(calificacionDetails.getValor());
                    }
                    return calificacionRepository.save(calificacion);
                })
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada con id: " + id));
    }

    public void eliminarCalificacion(Long id) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada con id: " + id));
        calificacionRepository.delete(calificacion);
    }

    @Transactional(readOnly = true)
    public List<Calificacion> obtenerTodasCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Calificacion obtenerCalificacionPorId(Long id) {
        return calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada con id: " + id));
    }
}