package proyectoExamen.SpringBoot_Microservicios.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CalificacionNotFoundException extends RuntimeException {
    public CalificacionNotFoundException(String message) {
        super(message);
    }

    public CalificacionNotFoundException(Long calificacionId) {
        super("Calificación con ID " + calificacionId + " no registrada");
    }
}