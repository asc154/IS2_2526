package es.unican.is2.seguroscommon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class SeguroTest {

	@Test
    void CP01() {
        Seguro s = new Seguro(1L, "1234ABC", 80,
                Cobertura.TODO_RIESGO,
                LocalDate.now().minusDays(1));
        assertEquals(800.0, s.precio(), 0.001);
    }

    @Test
    void CP02() {
        Seguro s = new Seguro(1L, "1234ABC", 100,
                Cobertura.TODO_RIESGO,
                LocalDate.now().minusDays(1));
        assertEquals(840.0, s.precio(), 0.001);
    }

    @Test
    void CP03() {
        Seguro s = new Seguro(1L, "1234ABC", 120,
                Cobertura.TODO_RIESGO,
                LocalDate.now().minusDays(1));
        assertEquals(960.0, s.precio(), 0.001);
    }

    @Test
    void CP04() {
        Seguro s = new Seguro(1L, "1234ABC", 100,
                Cobertura.TERCEROS_LUNAS,
                LocalDate.now().minusDays(1));
        assertEquals(504.0, s.precio(), 0.001);
    }

    @Test
    void CP05() {
        Seguro s = new Seguro(1L, "1234ABC", 120,
                Cobertura.TERCEROS,
                LocalDate.now().minusDays(1));
        assertEquals(384.0, s.precio(), 0.001);
    }

    @Test
    void CP06() {
        Seguro s = new Seguro(1L, "1234ABC", 90,
                Cobertura.TERCEROS,
                LocalDate.now());
        assertEquals(336.0, s.precio(), 0.001);
    }

    @Test
    void CP07() {
        Seguro s = new Seguro(1L, "1234ABC", 80,
                Cobertura.TERCEROS,
                LocalDate.now().minusYears(1));
        assertEquals(320.0, s.precio(), 0.001);
    }

    @Test
    void CP08() {
        Seguro s = new Seguro(1L, "1234ABC", 89,
                Cobertura.TODO_RIESGO,
                LocalDate.now().minusYears(1).minusDays(1));
        assertEquals(1000.0, s.precio(), 0.001);
    }

    // ----- NO VÁLIDOS -----

    @Test
    void CP09() {
        Seguro s = new Seguro(1L, "1234ABC", 100,
                Cobertura.TODO_RIESGO,
                null);
        assertEquals(0.0, s.precio(), 0.001);
    }

    @Test
    void CP10() {
        Seguro s = new Seguro(1L, "1234ABC", 100,
                Cobertura.TODO_RIESGO,
                LocalDate.now().plusDays(1));
        assertEquals(0.0, s.precio(), 0.001);
    }

    @Test
    void CP11() {
        Seguro s = new Seguro(1L, "1234ABC", 100,
                null,
                LocalDate.now().minusDays(1));
        assertEquals(0.0, s.precio(), 0.001);
    }
    
 // ----- Tests para Equals y HashCode -----

    @Test
    void testEqualsAndHashCode() {
        Seguro s1 = new Seguro();
        s1.setId(1L);
        Seguro s2 = new Seguro();
        s2.setId(1L);
        Seguro s3 = new Seguro();
        s3.setId(2L);

        // Identidad (this == obj)
        assertTrue(s1.equals(s1));

        // Null y Clase distinta
        assertFalse(s1.equals(null));
        assertFalse(s1.equals(new Object()));
        assertFalse(s1.equals("No soy un seguro"));

        // Comparación de IDs (Iguales)
        assertTrue(s1.equals(s2));
        assertEquals(s1.hashCode(), s2.hashCode());

        // Comparación de IDs (Distintos)
        assertFalse(s1.equals(s3));
        assertNotEquals(s1.hashCode(), s3.hashCode());
    }
    
    
}
