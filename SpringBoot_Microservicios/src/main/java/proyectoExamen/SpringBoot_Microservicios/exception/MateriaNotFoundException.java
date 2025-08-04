package proyectoExamen.SpringBoot_Microservicios.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MateriaNotFoundException extends RuntimeException {
    public MateriaNotFoundException(String message) {
        super(message);
    }

    public MateriaNotFoundException(Long materiaId) {
        super("Materia con ID " + materiaId + " no existe en el catálogo");
    }
}