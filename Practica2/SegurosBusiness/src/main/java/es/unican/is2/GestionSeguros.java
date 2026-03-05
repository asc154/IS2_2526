package es.unican.is2;

import java.util.List;

/**
 * Clase de la capa de negocio que gestiona los clientes y sus seguros.
 */
public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    // Atributos (Basados en la descripción de la aplicación) 
    // Nota: Aquí se incluiría la referencia al DAO/Persistencia
	
	private IClientesDAO clientesDAO; 
	private ISegurosDAO segurosDAO;	
    
	public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        // En esta fase solo definimos la cabecera y asignamos atributos
        // para permitir la compilación en el paso 3.3.
		this.clientesDAO = clientesDAO;
		this.segurosDAO = segurosDAO;

    }

	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		//Delegamos búsqueda al DAO de clientes
        return clientesDAO.cliente(dni);
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
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
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
		// Lógica de negocio: Verificar si el cliente ya existe antes de crearlo
        if (clientesDAO.cliente(c.getDni()) != null) {
            return null; 
        }       
        return clientesDAO.creaCliente(c);
	}

	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
		// 1. Buscamos si el cliente existe
        Cliente cliente = clientesDAO.cliente(dni);
        
        if (cliente == null) {
            return null; // No existe, no hay nada que eliminar
        }
        
        //Lógica de negocio: No se puede borrar si tiene seguros asociados        
        if (cliente.getSeguros() != null && !cliente.getSeguros().isEmpty()) {
            throw new OperacionNoValida("No se puede dar de baja a un cliente con seguros activos.");
        }
        
        //Si no tiene seguros, procedemos a la eliminación
        return clientesDAO.eliminaCliente(dni);
	}

   
}
