package proyectoExamen.SpringBoot_Microservicios.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlumnoNotFoundException extends RuntimeException {
    public AlumnoNotFoundException(String message) {
        super(message);
    }

    public AlumnoNotFoundException(String matricula, String operation) {
        super("Alumno con matrícula '" + matricula + "' no encontrado. Operación fallida: " + operation);
    }
}
