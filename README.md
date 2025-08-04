# Sistema de Gestión Académica - Spring Boot

Aplicación para registro de alumnos y calificaciones desarrollada con Spring Boot 3.2.0

## 📦 Tecnologías Utilizadas

- Spring Boot 3.2.0
- Spring Data JPA
- Base de datos H2 (en memoria)
- Lombok (actualmente en resolución de problemas)
- Swagger/OpenAPI (en proceso de configuración)
- Maven

## ⚠️ Estado Actual

Estamoy trabajando activamente para resolver:
- Configuración completa de Lombok para simplificar el código
- Integración óptima de Swagger para documentación API
- Mejoras en los tests de integración

## 🚀 Cómo Ejecutar el Proyecto

1. Clona el repositorio:

git clone https://github.com/RicardoCaballero26/examen-tecnico-Java-SpringBoot.git
Compila y ejecuta:


mvn clean install
mvn spring-boot:run
Accede a:

API: http://localhost:8080

Consola H2: http://localhost:8080/h2-console
(Credenciales: JDBC URL: jdbc:h2:mem:examen_db, User: sa, Password: vacío)

🔍 Endpoints Disponibles
Módulo de Alumnos
POST /api/alumnos - Registrar nuevo alumno

GET /api/alumnos - Listar todos los alumnos

Módulo de Calificaciones
POST /api/calificaciones - Registrar calificación

GET /api/calificaciones - Consultar calificaciones

🛠️ Problemas Conocidos
Tecnología	Estado	Solución en Progreso
Lombok	⚠️ Configuración en proceso	Optimizando anotaciones
Swagger	⚠️ Integración parcial	Mejorando documentación
🤝 Cómo Contribuir
Si deseas colaborar en la solución de los problemas actuales:


📧 Contacto
Si tienes preguntas sobre el proyecto:
Ricardo Caballero
📧 richyartcaballero@gmail.com

