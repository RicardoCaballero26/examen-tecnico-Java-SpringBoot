package proyectoExamen.SpringBoot_Microservicios.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyectoExamen.SpringBoot_Microservicios.dto.AlumnoDTO;
import proyectoExamen.SpringBoot_Microservicios.exception.DuplicateAlumnoException;
import proyectoExamen.SpringBoot_Microservicios.exception.MateriaNotFoundException;
import proyectoExamen.SpringBoot_Microservicios.model.Alumno;
import proyectoExamen.SpringBoot_Microservicios.model.Materia;
import proyectoExamen.SpringBoot_Microservicios.repository.AlumnoRepository;
import proyectoExamen.SpringBoot_Microservicios.repository.MateriaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlumnoServiceTest {

    private static final String NOMBRE = "María";
    private static final String APELLIDO_PATERNO = "García";
    private static final String APELLIDO_MATERNO = "López";
    private static final Long MATERIA_ID = 1L;
    private static final String MATRICULA_PREFIX = "ALU-";

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private MateriaRepository materiaRepository;

    @InjectMocks
    private AlumnoService alumnoService;

    private AlumnoDTO alumnoDTO;
    private Materia materia;

    @BeforeEach
    void setUp() {
        // Configuración del DTO de prueba
        alumnoDTO = AlumnoDTO.builder()
                .nombre(NOMBRE)
                .apellidoPaterno(APELLIDO_PATERNO)
                .apellidoMaterno(APELLIDO_MATERNO)
                .materiaId(MATERIA_ID)
                .build();

        // Configuración de materia de prueba
        materia = Materia.builder()
                .id(MATERIA_ID)
                .nombre("Matemáticas")
                .build();
    }

    @Test
    @DisplayName("Registrar alumno exitosamente")
    void registrarAlumno_deberiaRegistrarCorrectamente() {
        // Configuración de mocks
        when(materiaRepository.findById(MATERIA_ID)).thenReturn(Optional.of(materia));
        when(alumnoRepository.existsByNombreAndApellidoPaternoAndApellidoMaterno(
                NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO)).thenReturn(false);
        when(alumnoRepository.save(any(Alumno.class))).thenAnswer(invocation -> {
            Alumno alumno = invocation.getArgument(0);
            alumno.setMatricula("ALU-ABC123"); // Simulamos matrícula generada
            return alumno;
        });

        // Ejecución
        AlumnoDTO resultado = alumnoService.registrarAlumno(alumnoDTO);

        // Verificaciones
        assertNotNull(resultado);
        assertNotNull(resultado.getMatricula());
        assertTrue(resultado.getMatricula().startsWith(MATRICULA_PREFIX));
        assertEquals(NOMBRE, resultado.getNombre());
        assertEquals(APELLIDO_PATERNO, resultado.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO, resultado.getApellidoMaterno());
        assertEquals(MATERIA_ID, resultado.getMateriaId());

        verify(materiaRepository).findById(MATERIA_ID);
        verify(alumnoRepository).existsByNombreAndApellidoPaternoAndApellidoMaterno(
                NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO);
        verify(alumnoRepository).save(any(Alumno.class));
    }

    @Test
    @DisplayName("Lanzar excepción cuando la materia no existe")
    void registrarAlumno_deberiaLanzarMateriaNotFoundException() {
        when(materiaRepository.findById(MATERIA_ID)).thenReturn(Optional.empty());

        assertThrows(MateriaNotFoundException.class, () -> {
            alumnoService.registrarAlumno(alumnoDTO);
        });

        verify(materiaRepository).findById(MATERIA_ID);
        // Cambiado a verificar que no se guarda el alumno
        verify(alumnoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Lanzar excepción cuando el alumno ya existe")
    void registrarAlumno_deberiaLanzarDuplicateAlumnoException() {
        // Solo mockear lo estrictamente necesario
        when(alumnoRepository.existsByNombreAndApellidoPaternoAndApellidoMaterno(
                NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO)).thenReturn(true);

        assertThrows(DuplicateAlumnoException.class, () -> {
            alumnoService.registrarAlumno(alumnoDTO);
        });

        // Verificar que no se guardó ningún alumno
        verify(alumnoRepository, never()).save(any());
        // Verificar que no se buscó materia (según tu implementación actual)
        verify(materiaRepository, never()).findById(any());
    }

    @Test
    @DisplayName("Generar matrícula con formato correcto")
    void registrarAlumno_deberiaGenerarMatriculaValida() {
        when(materiaRepository.findById(MATERIA_ID)).thenReturn(Optional.of(materia));
        when(alumnoRepository.existsByNombreAndApellidoPaternoAndApellidoMaterno(
                NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO)).thenReturn(false);

        when(alumnoRepository.save(any(Alumno.class))).thenAnswer(invocation -> {
            Alumno alumno = invocation.getArgument(0);
            // Verificar que el servicio generó la matrícula correctamente
            assertNotNull(alumno.getMatricula());
            assertTrue(alumno.getMatricula().startsWith(MATRICULA_PREFIX));
            return alumno;
        });

        AlumnoDTO resultado = alumnoService.registrarAlumno(alumnoDTO);

        assertNotNull(resultado.getMatricula());
        assertTrue(resultado.getMatricula().startsWith(MATRICULA_PREFIX));
        assertEquals(12, resultado.getMatricula().length()); // ALU- + 8 caracteres
    }

    @Test
    @DisplayName("No debería validar materia si ya existe alumno duplicado")
    void registrarAlumno_noDeberiaValidarMateriaSiAlumnoDuplicado() {
        when(alumnoRepository.existsByNombreAndApellidoPaternoAndApellidoMaterno(
                NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO)).thenReturn(true);

        assertThrows(DuplicateAlumnoException.class, () -> {
            alumnoService.registrarAlumno(alumnoDTO);
        });

        // Verificar que no se buscó la materia
        verify(materiaRepository, never()).findById(any());
    }
}