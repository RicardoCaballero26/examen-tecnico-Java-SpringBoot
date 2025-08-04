package proyectoExamen.SpringBoot_Microservicios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateAlumnoException extends RuntimeException {
    public DuplicateAlumnoException(String message) {
        super(message);
    }

    public DuplicateAlumnoException(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super("Alumno '" + nombre + " " + apellidoPaterno + " " + apellidoMaterno + "' ya está registrado");
    }
}