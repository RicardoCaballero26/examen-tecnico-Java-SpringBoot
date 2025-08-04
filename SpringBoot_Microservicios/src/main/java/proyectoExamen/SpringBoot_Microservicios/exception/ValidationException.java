package proyectoExamen.SpringBoot_Microservicios.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String field, String error) {
        super("Error en campo '" + field + "': " + error);
    }
}