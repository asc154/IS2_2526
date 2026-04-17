package es.unican.is2.seguroscommon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class SeguroTest {

    /* * CASOS VÁLIDOS 
     */

    @Test
    void CP01_fechaAyer_TODO_RIESGO() {
        // Lógica: 1000 (Base) + 0% (Potencia < 90) = 1000. 
        // 1000 - 20% (Antigüedad > 1 día) = 800.0
        Seguro s = new Seguro(1L, "1234ABC", 80, Cobertura.TODO_RIESGO, LocalDate.now().minusDays(1));
        assertEquals(800.0, s.precio(), 0.001);
    }

    @Test
    void CP02_fechaAyer_TODO_RIESGO() {
        // Lógica: 1000 (Base) + 5% (Potencia 90-110) = 1050.
        // 1050 - 20% (Antigüedad > 1 día) = 840.0
        Seguro s = new Seguro(1L, "1234ABC", 100, Cobertura.TODO_RIESGO, LocalDate.now().minusDays(1));
        assertEquals(840.0, s.precio(), 0.001);
    }

    @Test
    void CP03_fechaAyer_TODO_RIESGO() {
        // Lógica: 1000 (Base) + 20% (Potencia > 110) = 1200.
        // 1200 - 20% (Antigüedad) = 960.0
        Seguro s = new Seguro(1L, "1234ABC", 120, Cobertura.TODO_RIESGO, LocalDate.now().minusDays(1));
        assertEquals(960.0, s.precio(), 0.001);
    }

    @Test
    void CP04_fechaAyer_TERCEROS_LUNAS() {
        // Lógica: 600 (Base) + 5% (Potencia 100) = 630.
        // 630 - 20% (Antigüedad) = 504.0
        Seguro s = new Seguro(1L, "1234ABC", 100, Cobertura.TERCEROS_LUNAS, LocalDate.now().minusDays(1));
        assertEquals(504.0, s.precio(), 0.001);
    }

    @Test
    void CP05_fechaAyer_TERCEROS() {
        // Lógica: 400 (Base) + 20% (Potencia 120) = 480.
        // 480 - 20% (Antigüedad) = 384.0
        Seguro s = new Seguro(1L, "1234ABC", 120, Cobertura.TERCEROS, LocalDate.now().minusDays(1));
        assertEquals(384.0, s.precio(), 0.001);
    }

    @Test
    void CP06_fechaHoy_TERCEROS() {
        // Lógica: 400 (Base) + 5% (Potencia 90) = 420.
        // 420 - 20% (Antigüedad hoy o antes) = 336.0
        Seguro s = new Seguro(1L, "1234ABC", 90, Cobertura.TERCEROS, LocalDate.now());
        assertEquals(336.0, s.precio(), 0.001);
    }
    
    @Test
    void CP07_fechaExactamenteUnAnio_TERCEROS() {
        // Según especificación el primer año incluye el día exacto → 400*0.80 = 320
        Seguro s = new Seguro(1L, "1234ABC", 80, Cobertura.TERCEROS,
                LocalDate.now().minusYears(1));
        assertEquals(320.0, s.precio(), 0.01);
    }

    /* * CASOS NO VÁLIDOS / LÍMITE 
     */

    @Test
    void CP08_fechaNull() {
        Seguro s = new Seguro(1L, "1234ABC", 100, Cobertura.TODO_RIESGO, null);
        assertEquals(0.0, s.precio(), 0.001);
    }

    @Test
    void CP09_fechaFutura() {
        // Mañana no debería permitir calcular precio (o devolver 0 según lógica)
        Seguro s = new Seguro(1L, "1234ABC", 100, Cobertura.TODO_RIESGO, LocalDate.now().plusDays(1));
        assertEquals(0.0, s.precio(), 0.001);
    }

    @Test
    void CP10_coberturaNull() {
        Seguro s = new Seguro(1L, "1234ABC", 100, null, LocalDate.now().minusDays(1));
        assertEquals(0.0, s.precio(), 0.001);
    }
    
    
}
