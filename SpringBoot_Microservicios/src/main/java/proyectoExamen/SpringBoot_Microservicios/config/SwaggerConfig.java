package proyectoExamen.SpringBoot_Microservicios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Registro de Alumnos y Calificaciones")
                        .version("1.0")
                        .description("Documentación de los endpoints para el módulo de alumnos y calificaciones.")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tucorreo@example.com")
                        )
                );
    }
}