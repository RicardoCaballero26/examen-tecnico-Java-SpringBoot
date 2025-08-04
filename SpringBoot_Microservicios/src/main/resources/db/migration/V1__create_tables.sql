CREATE TABLE IF NOT EXISTS materias (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    clave VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS alumnos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    materia_id BIGINT,
    FOREIGN KEY (materia_id) REFERENCES materias(id)
);

CREATE TABLE IF NOT EXISTS calificaciones (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    alumno_id BIGINT NOT NULL,
    materia_id BIGINT NOT NULL,
    valor DECIMAL(4,2) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (alumno_id) REFERENCES alumnos(id),
    FOREIGN KEY (materia_id) REFERENCES materias(id)
);