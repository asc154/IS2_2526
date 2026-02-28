package es.unican.is2;

import java.util.List;

/**
 * Clase de la capa de negocio que gestiona los clientes y sus seguros.
 */
public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    // Atributos (Basados en la descripción de la aplicación) 
    // Nota: Aquí se incluiría la referencia al DAO/Persistencia
    
	public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        // En esta fase solo definimos la cabecera y asignamos atributos
        // para permitir la compilación en el paso 3.3.

    }

    /**
     * Caso de Uso: Nuevo Cliente 
     */
    public void nuevoCliente(String nombre, String dni, boolean minusvalia) {
        // TODO: Implementar según pasos de la secuencia 
    }

    /**
     * Caso de Uso: Baja Cliente 
     */
    public Cliente bajaCliente(String dni) {
        // TODO: Implementar según pasos de la secuencia 
    	return null;
    }

    /**
     * Caso de Uso: Nuevo Seguro 
     */
    public void nuevoSeguro(String dni, String matricula, int potencia, String tipoCobertura) {
        // TODO: Implementar según pasos de la secuencia 
    }

    /**
     * Caso de Uso: Baja Seguro 
     */
    public Seguro bajaSeguro(String dni, String matricula) {
        // TODO: Implementar según pasos de la secuencia 
    	return null;
    }

    /**
     * Caso de Uso: Consulta Cliente 
     * Retorna el objeto Cliente con sus seguros y total a pagar.
     */
    public void consultaCliente(String dni) {
        // TODO: Implementar visualización en interfaz 
    }

    /**
     * Caso de Uso: Consulta Seguro 
     */
    public void consultaSeguro(String matricula) {
        // TODO: Implementar 
    }

    /**
     * Caso de Uso: Añadir conductor adicional 
     */
    public void anhadirConductorAdicional(String matricula, String nombreConductor) {
        // TODO: Implementar 
    }

	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
