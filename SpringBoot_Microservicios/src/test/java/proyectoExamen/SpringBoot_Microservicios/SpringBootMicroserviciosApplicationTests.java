package proyectoExamen.SpringBoot_Microservicios;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class SpringBootMicroserviciosApplicationTests {

	@Test
	void contextLoads() {
		// Test de carga de contexto
	}
}