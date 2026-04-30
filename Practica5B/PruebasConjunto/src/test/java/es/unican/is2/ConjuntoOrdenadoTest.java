package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.adt.IConjuntoOrdenado;

public class ConjuntoOrdenadoTest {

    private IConjuntoOrdenado<Integer> conjuntoVacio;
    private IConjuntoOrdenado<Integer> conjuntoLleno;

    @BeforeEach
    public void setUp() {
        // Inicializamos los conjuntos antes de cada test
        conjuntoVacio = new ConjuntoOrdenado<Integer>();
        
        conjuntoLleno = new ConjuntoOrdenado<Integer>();
        conjuntoLleno.add(10);
        conjuntoLleno.add(20);
        conjuntoLleno.add(30);
    }

    @Test
    public void testAddValido() {
        assertTrue(conjuntoVacio.add(10));
        assertEquals(1, conjuntoVacio.size());
        assertEquals(10, conjuntoVacio.get(0));

        assertTrue(conjuntoLleno.add(25)); // Insertamos algo nuevo
        assertEquals(4, conjuntoLleno.size());

        assertFalse(conjuntoLleno.add(10));
        assertEquals(4, conjuntoLleno.size()); // El tamaño no cambia
    }

    @Test
    public void testGetValido() {
        assertEquals(10, conjuntoLleno.get(0));

        assertEquals(30, conjuntoLleno.get(2));
    }

    @Test
    public void testRemoveValido() {
        assertEquals(10, conjuntoLleno.remove(0));
        assertEquals(2, conjuntoLleno.size());

        setUp();
        
        assertEquals(30, conjuntoLleno.remove(2));
        assertEquals(2, conjuntoLleno.size());
    }

    @Test
    public void testSizeValido() {
        assertEquals(0, conjuntoVacio.size());

        assertEquals(3, conjuntoLleno.size());
    }

    @Test
    public void testClearValido() {
        conjuntoVacio.clear();
        assertEquals(0, conjuntoVacio.size());

        conjuntoLleno.clear();
        assertEquals(0, conjuntoLleno.size());
    }


    @Test
    public void testAddNoValido() {
        assertThrows(NullPointerException.class, () -> conjuntoVacio.add(null));

        assertThrows(NullPointerException.class, () -> conjuntoLleno.add(null));
    }

    @Test
    public void testGetNoValido() {
        assertThrows(IndexOutOfBoundsException.class, () -> conjuntoVacio.get(0));

        assertThrows(IndexOutOfBoundsException.class, () -> conjuntoLleno.get(-1));

        assertThrows(IndexOutOfBoundsException.class, () -> conjuntoLleno.get(3));
    }

    @Test
    public void testRemoveNoValido() {
        assertThrows(IndexOutOfBoundsException.class, () -> conjuntoVacio.remove(0));

        assertThrows(IndexOutOfBoundsException.class, () -> conjuntoLleno.remove(-1));

        assertThrows(IndexOutOfBoundsException.class, () -> conjuntoLleno.remove(3));
    }
}