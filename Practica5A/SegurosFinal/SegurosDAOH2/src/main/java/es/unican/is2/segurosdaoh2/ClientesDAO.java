package es.unican.is2.segurosdaoh2;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.seguroscommon.Cliente;
import es.unican.is2.seguroscommon.DataAccessException;
import es.unican.is2.seguroscommon.IClientesDAO;
import es.unican.is2.seguroscommon.Seguro;


public class ClientesDAO implements IClientesDAO {

	@Override
	public Cliente creaCliente(Cliente c) throws DataAccessException {
		String insertStatement = String.format(
				"insert into Clientes(dni, nombre, minusvalia) values ('%s', '%s', '%b')",
				c.getDni(),
				c.getNombre(),
				c.getMinusvalia());
		H2ServerConnectionManager.executeSqlStatement(insertStatement);
		return c;
	}

	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		Cliente result = null; 
		Connection con = H2ServerConnectionManager.getConnection();
		// Mejora 4: Sustituimos el "select *" por las columnas específicas
		String statementText = "select dni, nombre, minusvalia from Clientes where dni = '"+ dni+"'";
		// Mejora 1: Usamos try-with-resources para el Statement. Se cerrará automáticamente.
		try (Statement statement = con.createStatement()) {
			ResultSet results = statement.executeQuery(statementText);
			if (results.next()) { 
				result = procesaCliente(con,results);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}
		return result;
	}

	@Override
	public Cliente actualizaCliente(Cliente nuevo) throws DataAccessException {
		Cliente cliente = null;
		Cliente old = cliente(nuevo.getDni());
		String statementText;

		// Mejora 3: Se elimina 'Connection con = H2ServerConnectionManager.getConnection();' 

		statementText = String.format(
				"update Clientes set nombre = '%s', minusvalia = '%b' where dni = '%s'", 
				nuevo.getNombre(), nuevo.getMinusvalia(), nuevo.getDni());
			H2ServerConnectionManager.executeSqlStatement(statementText);
			for(Seguro s: old.getSeguros()) {
				if (!nuevo.getSeguros().contains(s)) {
					statementText = String.format(
						"update Seguros set cliente_FK = null where id = '%d'",
						s.getId());
					H2ServerConnectionManager.executeSqlStatement(statementText);
				}
			}
			cliente = cliente(nuevo.getDni());
		
		return cliente;
	}

	@Override
	public Cliente eliminaCliente(String dni) throws DataAccessException {
		Cliente cliente = cliente(dni);
		// Mejora 3: Se elimina 'Connection con = H2ServerConnectionManager.getConnection();' 
		
		String statementText = "delete from Clientes where dni = " + dni;
		H2ServerConnectionManager.executeSqlStatement(statementText);
		return cliente;
	}

	@Override
	public List<Cliente> clientes() throws DataAccessException {
		List<Cliente> clientes = new LinkedList<Cliente>();
		Connection con = H2ServerConnectionManager.getConnection(); 
		// Mejora 4: Sustituimos el "select *" por las columnas específicas
		String statementText = "select dni, nombre, minusvalia from Clientes"; 
		
		// Mejora 1: Usamos try-with-resources para el Statement.
		try (Statement statement = con.createStatement()) { 
			ResultSet results = statement.executeQuery(statementText); 
			while (results.next()) {
				clientes.add(procesaCliente(con, results)); 
			}
		} catch (SQLException e) {
			throw new DataAccessException();
		}

		return clientes;
	}

	private Cliente procesaCliente(Connection con, ResultSet results) throws SQLException, DataAccessException {
		Cliente result = ClienteMapper.toCliente(results);
		// Cargamos los seguros del cliente
		// Mejora 1: try-with-resources para el Statement
		try (Statement statement = con.createStatement()) {
			// Mejora 4: Sustituir "select *" por las columnas específicas de la tabla Seguros
			String statementText = String.format("select id, matricula, fechaInicio, cobertura, potencia, "
					+ "conductorAdicional from Seguros where cliente_FK = '%s'", result.getDni());			
			results = statement.executeQuery(statementText);
			while (results.next()) {
				result.getSeguros().add(SeguroMapper.toSeguro(results));
			}
		}		
		return result;
	}
	
}
