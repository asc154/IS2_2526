package es.unican.is2;

import java.util.List;

/**
 * Clase de la capa de negocio que gestiona los clientes y sus seguros.
 */
public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    // Atributos (Basados en la descripción de la aplicación) 
    private IClientesDAO clientesDAO;
    private ISegurosDAO segurosDAO;
    
	public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
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
		return segurosDAO.seguroPorMatricula(matricula);
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		Cliente c = clientesDAO.cliente(dni);
		if ( c == null) {
			throw new OperacionNoValida("No existe un cliente con el DNI indicado");
		}
		
		if (segurosDAO.seguroPorMatricula(s.getMatricula()) != null) {
			throw new OperacionNoValida("Ya existe un seguro para esa matrícula");
		}		
		c.getSeguros().add(s);
		segurosDAO.actualizaSeguro(s);       
		clientesDAO.actualizaCliente(c);		
		return s;
	}

	@Override
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {

				Seguro s = segurosDAO.seguroPorMatricula(matricula); 
				if (s == null) {
					throw new OperacionNoValida("No existe un seguro asociado a la matrícula indicada");
				}
				Cliente c = clientesDAO.cliente(dni);
				if (c == null) {
					throw new OperacionNoValida("No existe un cliente con el DNI indicado");
				}				
				boolean pertenece = false;
				for (Seguro seguroDelCliente : c.getSeguros()) {
					if (seguroDelCliente.getMatricula().equals(matricula)) {
						pertenece = true;
						break;
					}
				}				
				if (!pertenece) {
					throw new OperacionNoValida("El seguro no pertenece al cliente indicado");
				}
				c.getSeguros().remove(s);
				clientesDAO.actualizaCliente(c); 
				segurosDAO.eliminaSeguro(s.getId());
				return s;
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {

				Seguro s = segurosDAO.seguroPorMatricula(matricula); 
				if (s == null) {
					return null; 
				}	
				s.setConductorAdicional(conductor);
				segurosDAO.actualizaSeguro(s); 
				return s;
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
