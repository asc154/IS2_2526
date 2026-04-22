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
    
    
}
