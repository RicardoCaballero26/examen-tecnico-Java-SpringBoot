package proyectoExamen.SpringBoot_Microservicios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoExamen.SpringBoot_Microservicios.exception.MateriaNotFoundException;
import proyectoExamen.SpringBoot_Microservicios.model.Materia;
import proyectoExamen.SpringBoot_Microservicios.repository.MateriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;

    @Transactional(readOnly = true)
    public boolean existeMateria(Long materiaId) {
        return materiaRepository.existsById(materiaId);
    }

    @Transactional(readOnly = true)
    public Materia getMateriaEntity(Long materiaId) {
        return materiaRepository.findById(materiaId)
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));
    }

    @Transactional(readOnly = true)
    public List<Materia> listarTodas() {
        return materiaRepository.findAll();
    }
}