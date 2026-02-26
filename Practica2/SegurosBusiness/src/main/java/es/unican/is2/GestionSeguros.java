package es.unican.is2;

import java.util.List;

/**
 * Clase de la capa de negocio que gestiona los clientes y sus seguros.
 */
public class GestionSeguros {

    // Atributos (Basados en la descripción de la aplicación) [cite: 23, 149]
    // Nota: Aquí se incluiría la referencia al DAO/Persistencia
    
	public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        // En esta fase solo definimos la cabecera y asignamos atributos
        // para permitir la compilación en el paso 3.3[cite: 20].

    }

    /**
     * Caso de Uso: Nuevo Cliente [cite: 46]
     */
    public void nuevoCliente(String nombre, String dni, boolean minusvalia) {
        // TODO: Implementar según pasos de la secuencia [cite: 52]
    }

    /**
     * Caso de Uso: Baja Cliente [cite: 55]
     */
    public void bajaCliente(String dni) {
        // TODO: Implementar según pasos de la secuencia [cite: 61]
    }

    /**
     * Caso de Uso: Nuevo Seguro [cite: 66]
     */
    public void nuevoSeguro(String dni, String matricula, int potencia, String tipoCobertura) {
        // TODO: Implementar según pasos de la secuencia [cite: 73]
    }

    /**
     * Caso de Uso: Baja Seguro [cite: 79]
     */
    public void bajaSeguro(String dni, String matricula) {
        // TODO: Implementar según pasos de la secuencia [cite: 85]
    }

    /**
     * Caso de Uso: Consulta Cliente [cite: 98]
     * Retorna el objeto Cliente con sus seguros y total a pagar.
     */
    public void consultaCliente(String dni) {
        // TODO: Implementar visualización en interfaz [cite: 102, 136]
    }

    /**
     * Caso de Uso: Consulta Seguro [cite: 106]
     */
    public void consultaSeguro(String matricula) {
        // TODO: Implementar [cite: 109]
    }

    /**
     * Caso de Uso: Añadir conductor adicional [cite: 112]
     */
    public void anhadirConductorAdicional(String matricula, String nombreConductor) {
        // TODO: Implementar [cite: 118]
    }
}
