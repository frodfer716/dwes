package laboral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase contenedora del codigo Main del Punto 1 de la Parte 2
 */
public class EjecucionParte2P1 {
	
	/**
	 * Llamada al codigo Main del Punto 1 de la Parte 2
	 */
	public static void ejecucion() {
		char sexo;
		int categoria;
		double anyos, sueldo;
		Empleado empleado;
		Nomina n = new Nomina();
		PrintWriter salida = null;
		
		try {
			
			File archivo = new File ("C:\\Users\\PC\\Documents\\workspace\\PracticaNomina\\media\\empleados.txt");
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			salida = new PrintWriter("C:\\Users\\PC\\Documents\\workspace\\PracticaNomina\\media\\sueldos.dat");
			
			String linea;
			while ((linea=br.readLine())!=null) {
				String[] seccion = linea.split(",");
				
				sexo = seccion[2].charAt(0);
				categoria = Integer.parseInt(seccion[3]);
				anyos = Double.parseDouble(seccion[4]);
				
				empleado = new Empleado(seccion[0], seccion[1], sexo, categoria, anyos);
				sueldo = n.sueldo(empleado);
				
				salida.println("DNI: " + empleado.dni + ", Sueldo: " + sueldo);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (DatosNoCorrectosException e) {
			// TODO: handle exception
		} finally {
			salida.close();
			System.out.println("Terminado con exito.");
		}
	}

}
