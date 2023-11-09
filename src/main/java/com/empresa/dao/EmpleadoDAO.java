package com.empresa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.empresa.conexion.Conexion;
import com.empresa.model.Empleado;
import com.empresa.model.Nomina;

/**
 * Clase DAO del proyecto web
 */
public class EmpleadoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	/**
	 * metodo para conectar con BD
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

	/**
	 * metodo para obtener todos los empleados
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Empleado> obtenerEmpleados() throws SQLException {
		ResultSet resultSet = null;
		List<Empleado> lista = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados WHERE deleted = FALSE";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String dni = resultSet.getString(1);
				String nombre = resultSet.getString(2);
				char sexo = resultSet.getString(3).charAt(0);
				int categoria = resultSet.getInt(4);
				double anyos = resultSet.getDouble(5);
				lista.add(new Empleado(dni, nombre, sexo, categoria, anyos));
			}

			statement.close();
			connection.close();

		} catch (SQLException err) {
			err.printStackTrace();
		}

		return lista;
	}

	/**
	 * metodo para obtener empleado por dni
	 * 
	 * @param dniEmpleado
	 * @return
	 * @throws SQLException
	 */
	public Empleado obtenerEmpleado(String dniEmpleado) throws SQLException {
		ResultSet resultSet = null;
		Empleado e = new Empleado();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados WHERE dni =? AND deleted = false";
			statement = connection.prepareStatement(sql);
			statement.setString(1, dniEmpleado);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				e.setDni(resultSet.getString(1));
				e.setNombre(resultSet.getString(2));
				e.setSexo(resultSet.getString(3).charAt(0));
				e.setCategoria(resultSet.getInt(4));
				e.setAnyos(resultSet.getDouble(5));
			}

			statement.close();
			connection.close();

		} catch (SQLException err) {
			err.printStackTrace();
		}

		return e;
	}

	/**
	 * metodo para crear empleado
	 * 
	 * @param empleado
	 * @return
	 * @throws SQLException
	 */
	public boolean guardar(Empleado empleado) throws SQLException {
		Nomina nomina = new Nomina();
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES(?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getDni());
			statement.setString(2, empleado.getNombre());
			statement.setString(3, String.valueOf(empleado.getSexo()).toUpperCase());
			statement.setInt(4, empleado.getCategoria());
			statement.setDouble(5, empleado.getAnyos());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();

			sql = "INSERT INTO nominas (empleado_dni, nomina) VALUES(?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getDni());
			statement.setDouble(2, nomina.sueldo(empleado));

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();

			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	/**
	 * metodo para eliminar empleado de la vista (no de la BDD) usando flags
	 * 
	 * @param dniEmpleado
	 * @return
	 * @throws SQLException
	 */
	public boolean eliminar(String dniEmpleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);

			sql = "UPDATE empleados SET deleted=TRUE WHERE dni=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, dniEmpleado);

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	/**
	 * metodo para editar empleado
	 * 
	 * @param empleado
	 * @return
	 * @throws SQLException
	 */
	public boolean editar(Empleado empleado) throws SQLException {
		Nomina nomina = new Nomina();
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getNombre());
			statement.setString(2, String.valueOf(empleado.getSexo()).toUpperCase());
			statement.setInt(3, empleado.getCategoria());
			statement.setDouble(4, empleado.getAnyos());
			statement.setString(5, empleado.getDni());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();

			sql = "UPDATE nominas SET nomina=? WHERE empleado_dni=?";
			statement = connection.prepareStatement(sql);

			statement.setDouble(1, nomina.sueldo(empleado));
			statement.setString(2, empleado.getDni());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			// connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	/**
	 * metodo para obtener empleado por DNI
	 * 
	 * @param dniBusqueda
	 * @return
	 * @throws SQLException
	 */
	public List<Empleado> buscarPorDNI(String dniBusqueda) throws SQLException {
		List<Empleado> lista = new ArrayList<>();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM empleados WHERE dni = ? AND deleted = false";
		estadoOperacion = false;

		try {
			connection = obtenerConexion();
			statement = connection.prepareStatement(sql);
			statement.setString(1, dniBusqueda);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String dni = resultSet.getString(1);
				String nombre = resultSet.getString(2);
				char sexo = resultSet.getString(3).charAt(0);
				int categoria = resultSet.getInt(4);
				double anyos = resultSet.getDouble(5);

				lista.add(new Empleado(dni, nombre, sexo, categoria, anyos));
			}

			statement.close();
			connection.close();

		} catch (SQLException err) {
			err.printStackTrace();
		}

		return lista;
	}

	/**
	 * metodo para obtener empleado por nombre
	 * 
	 * @param nombreBusqueda
	 * @return
	 * @throws SQLException
	 */
	public List<Empleado> buscarPorNombre(String nombreBusqueda) throws SQLException {
		List<Empleado> lista = new ArrayList<>();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM empleados WHERE nombre LIKE ? AND deleted = false";
		estadoOperacion = false;

		try {
			connection = obtenerConexion();
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + nombreBusqueda + "%");
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String dni = resultSet.getString(1);
				String nombre = resultSet.getString(2);
				char sexo = resultSet.getString(3).charAt(0);
				int categoria = resultSet.getInt(4);
				double anyos = resultSet.getDouble(5);

				lista.add(new Empleado(dni, nombre, sexo, categoria, anyos));
			}

			statement.close();
			connection.close();

		} catch (SQLException err) {
			err.printStackTrace();
		}

		return lista;
	}

	/**
	 * metodo para obtener empleado por sexo
	 * 
	 * @param sexoBusqueda
	 * @return
	 * @throws SQLException
	 */
	public List<Empleado> buscarPorSexo(String sexoBusqueda) throws SQLException {
		List<Empleado> lista = new ArrayList<>();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM empleados WHERE sexo = ? AND deleted = false";
		estadoOperacion = false;

		try {
			connection = obtenerConexion();
			statement = connection.prepareStatement(sql);
			statement.setString(1, String.valueOf(sexoBusqueda));
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String dni = resultSet.getString(1);
				String nombre = resultSet.getString(2);
				char sexo = resultSet.getString(3).charAt(0);
				int categoria = resultSet.getInt(4);
				double anyos = resultSet.getDouble(5);

				lista.add(new Empleado(dni, nombre, sexo, categoria, anyos));
			}

			statement.close();
			connection.close();

		} catch (SQLException err) {
			err.printStackTrace();
		}

		return lista;
	}

	/**
	 * metodo para obtener empleado por categoria
	 * 
	 * @param categoriaBusqueda
	 * @return
	 * @throws SQLException
	 */
	public List<Empleado> buscarPorCategoria(String categoriaBusqueda) throws SQLException {
		List<Empleado> lista = new ArrayList<>();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM empleados WHERE categoria = ? AND deleted = false";
		estadoOperacion = false;

		try {
			connection = obtenerConexion();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, Integer.parseInt(categoriaBusqueda));
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String dni = resultSet.getString(1);
				String nombre = resultSet.getString(2);
				char sexo = resultSet.getString(3).charAt(0);
				int categoria = resultSet.getInt(4);
				double anyos = resultSet.getDouble(5);

				lista.add(new Empleado(dni, nombre, sexo, categoria, anyos));
			}

			statement.close();
			connection.close();

		} catch (SQLException err) {
			err.printStackTrace();
		}

		return lista;
	}

	/**
	 * metodo para obtener empleado por anyos trabajados
	 * 
	 * @param anyosBusqueda
	 * @return
	 * @throws SQLException
	 */
	public List<Empleado> buscarPorAnyosTrabajados(String anyosBusqueda) throws SQLException {
		List<Empleado> lista = new ArrayList<>();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM empleados WHERE anyos = ? AND deleted = false";
		estadoOperacion = false;

		try {
			connection = obtenerConexion();
			statement = connection.prepareStatement(sql);
			statement.setDouble(1, Double.parseDouble(anyosBusqueda));
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String dni = resultSet.getString(1);
				String nombre = resultSet.getString(2);
				char sexo = resultSet.getString(3).charAt(0);
				int categoria = resultSet.getInt(4);
				double anyos = resultSet.getDouble(5);

				lista.add(new Empleado(dni, nombre, sexo, categoria, anyos));
			}

			statement.close();
			connection.close();

		} catch (SQLException err) {
			err.printStackTrace();
		}

		return lista;
	}

	/**
	 * metodo para comprobar si un dni ya existe en la bdd
	 * 
	 * @param dni
	 * @return
	 * @throws SQLException
	 */
	public boolean existeDNI(String dni) throws SQLException {
		ResultSet resultSet = null;
		String sql = "SELECT COUNT(*) FROM empleados WHERE dni = ? AND deleted = false";
		connection = obtenerConexion();

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();

			if (resultSet.next() && resultSet.getInt(1) > 0) {
				// Si hay algún resultado, el DNI ya existe en la base de datos
				return true;
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			statement.close();
			connection.close();
		}

		return false;
	}

}
