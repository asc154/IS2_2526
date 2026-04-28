package es.unican.is2.seguroscommon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Seguro s1000; 
    private Seguro s600;  

    @BeforeEach
    void setUp() {
        // Configuramos objetos REALES de Seguro para que devuelvan 1000 y 600
        // s1000: Todo Riesgo (1000), Potencia 80 (no sube), Hoy-2años (no baja) = 1000
        s1000 = new Seguro(1L, "1234ABC", 80, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(2));
        
        // s600: Terceros Lunas (600), Potencia 80, Hoy-2años = 600
        s600 = new Seguro(2L, "5678DEF", 80, Cobertura.TERCEROS_LUNAS, LocalDate.now().minusYears(2));
    }

    @Test
    void CP01_listaVacia_sinMinusvalia() {
        Cliente c = new Cliente("1", "Pepe", false);
        assertEquals(0.0, c.totalSeguros(), 0.01);
    }

    @Test
    void CP02_listaVacia_conMinusvalia() {
        Cliente c = new Cliente("1", "Pepe", true);
        assertEquals(0.0, c.totalSeguros(), 0.01);
    }

    @Test
    void CP03_unSeguro_sinMinusvalia() {
        Cliente c = new Cliente("1", "Pepe", false);
        c.getSeguros().add(s1000); // Usamos método getSeguros()
        assertEquals(1000.0, c.totalSeguros(), 0.01);
    }

    @Test
    void CP04_unSeguro_conMinusvalia() {
        Cliente c = new Cliente("1", "Pepe", true);
        c.getSeguros().add(s1000);
        // 1000 * 0.75 = 750
        assertEquals(750.0, c.totalSeguros(), 0.01);
    }

    @Test
    void CP05_variosSeguros_sinMinusvalia() {
        Cliente c = new Cliente("1", "Pepe", false);
        c.getSeguros().add(s1000);
        c.getSeguros().add(s600);
        assertEquals(1600.0, c.totalSeguros(), 0.01);
    }

    @Test
    void CP06_variosSeguros_conMinusvalia() {
        Cliente c = new Cliente("1", "Pepe", true);
        c.getSeguros().add(s1000);
        c.getSeguros().add(s600);
        // (1000 + 600) * 0.75 = 1200
        assertEquals(1200.0, c.totalSeguros(), 0.01);
    }

    @Test
    void CP07_listaConNull_sinMinusvalia() {
        Cliente c = new Cliente("1", "Pepe", false);
        c.getSeguros().add(s1000);
        c.getSeguros().add(null); // Esto probará  "if (s != null)"
        assertEquals(1000.0, c.totalSeguros(), 0.01);
    }

    @Test
    void CP08_listaNula_lanzaExcepcion() {
        Cliente c = new Cliente("1", "Pepe", false);
        c.setSeguros(null); // Forzamos que la lista sea null
        // Al ser la lista null, el for (Seguro s : seguros) lanzará NPE
        assertThrows(NullPointerException.class, () -> c.totalSeguros());
    }
}

