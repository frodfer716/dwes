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
	
	/**
	 * Llamada al codigo Main del Punto 2 de la Parte 2
	 */
	public static void ejecucion() {
		List<Empleado> empleados = new ArrayList<>();
		
		Connection conn = null;
        Statement st = null;
        @SuppressWarnings("unused")
		ResultSet rs = null;
        
        try {
        	conn = DBUtils.getConnection();
        	st = conn.createStatement();
        	
        	empleados.add(new Empleado("James Cosling", "32000032G", 'M', 4, 7));
        	empleados.add(new Empleado("Ada Lovelace", "32000031R", 'F'));
        	
        	for (int i = 0; i < empleados.size(); i++) {
        		Empleado e = empleados.get(i);
        		
        		int numFilasEmpleado = st.executeUpdate("INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES ('" + e.dni + "', '" + e.nombre + "', '" + e.sexo + "', " + e.getCategoria() + ", " + e.anyos + ")");
        		System.out.println("Empleado insertado ("+ numFilasEmpleado +" filas) con exito.");
    		}
        	
        	empleados.get(0).setCategoria(9);
        	empleados.get(1).incrAnyo();
        	System.out.println("Empleados actualizados.");
        	
        	rs = st.executeQuery("UPDATE empleados SET categoria = " + empleados.get(0).getCategoria() +" WHERE dni = '32000032G'");
        	System.out.println("Categoria actualizada con exito.");
        	rs = st.executeQuery("UPDATE empleados SET anyos = " + empleados.get(1).anyos +" WHERE dni = '32000031R'");
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
        } catch (DatosNoCorrectosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                System.out.println("Terminado con exito.");
            } catch (SQLException ex) {
                System.out.println("Ocurrió una excepción al cerrar la BD");
            }
        }
	}

}
