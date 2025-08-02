package proyecto1.examen;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ExamenApplicationTests {

	@Test
	void contextLoads() {
		// Test vacío que solo verifica que el contexto se carga
	}
}