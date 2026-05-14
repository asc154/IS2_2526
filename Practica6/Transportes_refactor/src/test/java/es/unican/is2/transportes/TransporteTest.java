package es.unican.is2.transportes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransporteTest {

    @Test
    public void testConstructorTransporteMercancias() {
        // Casos validos
        TransporteMercancias sut = new TransporteMercancias(1, 1);
        assertEquals(1, sut.horas());
        assertEquals(CategoriaTransporte.Mercancias, sut.categoria());
        assertEquals(1, sut.ton());
        assertEquals(0, sut.getPersonas());

        sut = new TransporteMercancias(10, 1000);
        assertEquals(10, sut.horas());
        assertEquals(CategoriaTransporte.Mercancias, sut.categoria());
        assertEquals(1000, sut.ton());
        assertEquals(0, sut.getPersonas());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
    }

    @Test
    public void testConstructorTransporteMercanciasPeligrosas() {
        // Casos validos
        TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(10, 1000);
        assertEquals(10, sut.horas());
        assertEquals(CategoriaTransporte.MercanciasPeligrosas, sut.categoria());
        assertEquals(1000, sut.ton());
        assertEquals(0, sut.getPersonas());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(10, 0));
    }

    @Test
    public void testConstructorTransportePersonas() {
        // Casos validos
        TransportePersonas sut = new TransportePersonas(10, 10);
        assertEquals(10, sut.horas());
        assertEquals(CategoriaTransporte.Personas, sut.categoria());
        assertEquals(10, sut.getPersonas());
        assertEquals(0, sut.ton());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(10, 0));
    }
}