package Laboral;

/**
 * Clase CalculaNominas con metodo Main
 */
public class CalculaNominas {
	
	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			/**
			 * Declaracion de empleados
			 */
			Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
			
			/**
			 * Salida por consola de los empleados
			 */
			System.out.println("Empleado 1:");
			escribe(e1);
			System.out.println();
			System.out.println("Empleado 2:");
			escribe(e2);
			System.out.println();
			
			/**
			 * Actualizacion de empleados
			 */
			System.out.println("Actualizacion empleados");
			e2.incrAnyo();
			e1.setCategoria(9);
			System.out.println();
			
			/**
			 * Salida por consola de los empleados actualizados
			 */
			System.out.println("Empleado 1:");
			escribe(e1);
			System.out.println();
			System.out.println("Empleado 2:");
			escribe(e2);
			
			/**
			 * Actualizacion de empleado e1 para prueba de errores
			 */
			System.out.println("Actualizacion e1 para prueba de errores");
			e1.setCategoria(11);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Salida por consola de todos los datos del empleado + su sueldo total
	 * @param e
	 */
	public static void escribe(Empleado e) {
		e.imprime();
		Nomina n = new Nomina();
		System.out.println("Sueldo: " + n.sueldo(e));
	}

}