package laboral;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase contenedora del codigo Main del Punto 2 de la Parte 2
 */
public class EjecucionParte2P2 {
	
	public static void ejecucion() throws DatosNoCorrectosException {
		List<Empleado> empleados = new ArrayList<>();
		
		Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
        	conn = DBUtils.getConnection();
        	st = conn.createStatement();
        	rs = st.executeQuery("SELECT * FROM empleados");
        	
        	while(rs.next()) {
        		String dni = rs.getString(2);
        		String nombre = rs.getString(1);
        		char sexo = rs.getString(3).charAt(0);
        		int categoria = rs.getInt(4);
        		double anyos = rs.getDouble(5);
        		empleados.add(new Empleado(dni, nombre, sexo, categoria, anyos));
        		System.out.println("Empleado leido con exito.");
        	}
        	
        	empleados.get(1).setCategoria(9);
        	empleados.get(0).incrAnyo();
        	
        	rs = st.executeQuery("UPDATE empleados SET categoria = " + empleados.get(1).getCategoria() +" WHERE dni = '32000032G'");
        	System.out.println("Categoria actualizada con exito.");
        	rs = st.executeQuery("UPDATE empleados SET anyos = " + empleados.get(0).anyos +" WHERE dni = '32000031R'");
        	System.out.println("Anyos actualizados con exito.");
        	
        	for (int i = 0; i < empleados.size(); i++) {
        		Empleado e = empleados.get(i);
        		Nomina n = new Nomina();
        		double sueldo_final = n.sueldo(e);
        		
        		int numFilas = st.executeUpdate("INSERT INTO nominas (empleado_dni, nomina) VALUES ('"+ e.dni +"', "+ sueldo_final +")");
        		System.out.println("Nomina insertada ("+ numFilas +" filas) con exito.");
			}
        	
        } catch(SQLException e){
            System.out.println("Ocurrió una excepción al conectar a la BD");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Ocurrió una excepción al cerrar la BD");
            }
        }
	}

}
